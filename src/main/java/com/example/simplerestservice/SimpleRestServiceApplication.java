package com.example.simplerestservice;

import com.example.simplerestservice.user.forms.CreateNewUserForm;
import com.example.simplerestservice.user.models.Role;
import com.example.simplerestservice.user.models.User;
import com.example.simplerestservice.user.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SimpleRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new CreateNewUserForm("test user", "username", "1234"));
            userService.saveUser(new CreateNewUserForm("test admin", "admin", "1234"));

            userService.addRoleToUser("username", "ROLE_USER");
            userService.addRoleToUser("admin", "ROLE_ADMIN");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
