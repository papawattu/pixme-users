package com.wattu.pixme.users.services;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.wattu.pixme.users.domain.User;
import com.wattu.pixme.users.domain.UserBuilder;

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

    public User createUser(User user) {
        // Logic to create a new user
        var exists = userRepository.findByEmail(user.email());
        if (exists.isPresent()) {
            throw new IllegalArgumentException("User with email " + user.email() + " already exists.");
        }
        var userWithId = new UserBuilder().setEmail(user.email()).setName(user.name()).build();
        userRepository.save(userWithId);
        return userWithId;
    }

    public Optional<User> getUserById(String id) {
        // Logic to retrieve a user by ID
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        // Logic to retrieve a user by email
        return userRepository.findByEmail(email);
    }
}
