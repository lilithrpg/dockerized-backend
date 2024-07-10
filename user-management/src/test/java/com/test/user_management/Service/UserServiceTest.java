package com.test.user_management.Service;

import com.test.user_management.Entity.UserEntity;
import com.test.user_management.Exception.DuplicateException;
import com.test.user_management.Exception.UserNotFoundException;
import com.test.user_management.Repository.UserRepository;
import com.test.user_management.Service.Impl.UserServiceImpl;
import com.test.user_management.dto.UserDetailsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private UserDetailsDto userDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetails = UserDetailsDto
                .builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("123456")
                .build();
    }

    @Test
    public void shouldRegisterUserSuccessfully_whenThePassedObjectIsCorrect() {
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(new UserEntity((userDetails))));
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity((userDetails)));

        UserEntity createdUser = userService.registerUser(userDetails);

        assertEquals("John Doe", createdUser.getName());
        assertEquals("john.doe@example.com", createdUser.getEmail());
    }

    @Test
    public void shouldReturnAnErrpr_whenThereIsAlreadyAnExistingPerson() {
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        assertThrows(DuplicateException.class, () -> {
            userService.registerUser(userDetails);
        });
    }
}
