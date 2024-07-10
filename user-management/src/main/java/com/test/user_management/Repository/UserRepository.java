package com.test.user_management.Repository;

import com.test.user_management.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByIdAndActive(Long id, boolean active);
    List<UserEntity> findAllByActive(boolean active);
}
