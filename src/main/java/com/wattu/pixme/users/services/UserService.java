package com.wattu.pixme.users.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wattu.pixme.users.domain.User;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User createUser(User user) {
        var exists = userRepository.findByEmail(user.email());
        if (exists.isPresent()) {
            throw new IllegalArgumentException("User with email " + user.email() + " already exists.");
        }
        var newUser = User.create(user.name(), user.email());
        userRepository.save(newUser);
        log.info("Created user id={} email={}", newUser.id(), newUser.email());
        return newUser;
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public boolean deleteUser(String id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        log.info("Deleted user id={}", id);
        return true;
    }
}
