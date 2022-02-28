package com.example.simplerestservice.user.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewUserForm {
    String name;
    String username;
    String password;
}
