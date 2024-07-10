package com.test.user_management.Controller;

import com.test.user_management.Entity.UserEntity;
import com.test.user_management.Service.UserService;
import com.test.user_management.dto.UserDetailsDto;
import com.test.user_management.dto.UserUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Register an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Registration",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "User already exists",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Missing Validation eg. Email is required or should be valid",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<UserEntity> registerUser(@Valid @RequestBody UserDetailsDto userDetails) {
        return new ResponseEntity<>(userService.registerUser(userDetails), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserEntity> editUser(@Valid @RequestBody UserUpdateDto userUpdate) {
        return ResponseEntity.ok(userService.updateUser(userUpdate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam Boolean active) {
        return ResponseEntity.ok(userService.getAllUsers(active));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }
}
