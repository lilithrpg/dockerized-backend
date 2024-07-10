package com.test.user_management.Service;

import com.test.user_management.Entity.UserEntity;
import com.test.user_management.dto.UserDetailsDto;
import com.test.user_management.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserEntity registerUser(UserDetailsDto userDetails);
    UserEntity updateUser(UserUpdateDto userUpdate);
    UserEntity getUserById(Long id);
    List<UserEntity> getAllUsers(Boolean active);
    void deactivateUser(Long id);
}
