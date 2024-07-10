package com.test.user_management;

import com.test.user_management.Controller.UserManagementController;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Bean
	public GroupedOpenApi userManagementApi() {
		return GroupedOpenApi.builder()
				.group("user-management")
				.displayName("User Management")
				.pathsToMatch("/v1/user/**")
				.build();
	}
}
