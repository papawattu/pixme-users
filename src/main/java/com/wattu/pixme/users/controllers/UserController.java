package com.wattu.pixme.users.controllers;

import java.util.List;
import com.wattu.pixme.users.domain.User;
import org.springframework.web.bind.annotation.RestController;

import com.wattu.pixme.users.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
