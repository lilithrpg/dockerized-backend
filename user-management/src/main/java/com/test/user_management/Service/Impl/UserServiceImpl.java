package com.test.user_management.Service.Impl;

import com.test.user_management.Entity.UserEntity;
import com.test.user_management.Exception.DuplicateException;
import com.test.user_management.Exception.LockedException;
import com.test.user_management.Exception.UserNotFoundException;
import com.test.user_management.Repository.UserRepository;
import com.test.user_management.Service.UserService;
import com.test.user_management.dto.Notification;
import com.test.user_management.dto.UserDetailsDto;
import com.test.user_management.dto.UserUpdateDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("notification.url")
    private String notificationUrl;

    @Override
    public UserEntity registerUser(UserDetailsDto userDetails) {
        if (userRepository.findByEmail(userDetails.getEmail()).isEmpty()) {
            throw new DuplicateException("User already exists");
        }
        UserEntity savedUser = userRepository.save(new UserEntity(userDetails));

        // should be async to @enableasnyc
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> entries = new HashMap<>();
        entries.put("name", userDetails.getName());
        Notification notification = Notification
                .builder()
                .notificationId("objectid")
                .recipient(userDetails.getEmail())
                .entries(entries)
                .build();
        restTemplate.postForObject(notificationUrl, notification, String.class);

        return savedUser;
    }

    @Override
    public UserEntity updateUser(UserUpdateDto userUpdate) {
        UserEntity user = userRepository
                .findByIdAndActive(userUpdate.getId(), true)
                .orElseThrow(UserNotFoundException::new);

        if (user.isLocked()) {
            throw new LockedException("Another user is currently updating this profile");
        }

        user.setLocked(true);
        userRepository.saveAndFlush(user);

        user.setName(userUpdate.getUserDetails().getName());
        user.setEmail(userUpdate.getUserDetails().getEmail());
        user.setLocked(false);
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findByIdAndActive(id, true).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserEntity> getAllUsers(Boolean active) {
        if (active == null) {
            return userRepository.findAll();
        } else {
            return userRepository.findAllByActive(active.equals(Boolean.TRUE));
        }
    }

    @Override
    public void deactivateUser(Long id) {
        UserEntity user = userRepository.findByIdAndActive(id, true).orElseThrow(UserNotFoundException::new);

        if (user.isLocked()) {
            throw new LockedException("Another user is currently updating this profile");
        }

        user.setLocked(true);
        userRepository.saveAndFlush(user);

        user.setActive(false);
        user.setLocked(false);
        userRepository.save(user);
    }
}
