package com.wattu.pixme.users.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wattu.pixme.users.domain.User;

@Component
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        // Logic to retrieve all users
        return userRepository.findAll();

    }

}
