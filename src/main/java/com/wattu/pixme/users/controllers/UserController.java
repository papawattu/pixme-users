package com.wattu.pixme.users.controllers;

import java.net.URI;
import java.util.List;
import com.wattu.pixme.users.domain.User;
import org.springframework.web.bind.annotation.RestController;

import com.wattu.pixme.users.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        var existingUser = userService.getUserByEmail(user.email());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with email " + user.email() + " already exists.");
        }
        var newUser = userService.createUser(user);
        return ResponseEntity.created(URI.create("/user/" + newUser.id())).body("User created successfully");
    }

}
