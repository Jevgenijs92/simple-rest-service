package com.example.simplerestservice.user.services;

import com.example.simplerestservice.user.forms.CreateNewUserForm;
import com.example.simplerestservice.user.models.Role;
import com.example.simplerestservice.user.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(CreateNewUserForm user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
