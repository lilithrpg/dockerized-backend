package com.test.user_management.Entity;

import com.test.user_management.dto.UserDetailsDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean active;
    private boolean locked;

    public UserEntity(UserDetailsDto userDetails) {
        this.name = userDetails.getName();
        this.email = userDetails.getEmail();
        this.password = userDetails.getPassword();
        this.active = true;
    }

    public UserEntity() {}
}
