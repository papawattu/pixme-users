package com.wattu.pixme.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wattu.pixme.users.domain.User;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        // Logic to retrieve all users from the data source
        // Using DataClassRowMapper for records (Spring 6+)
        return jdbcTemplate.query("SELECT * FROM users",
                DataClassRowMapper.newInstance(User.class));
    }

    public void save(User user) {
        // Logic to save a new user to the data source
        String sql = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.id(), user.name(), user.email());
    }

    public Optional<User> findById(String id) {
        // Logic to find a user by ID
        String sql = "SELECT * FROM users WHERE id = ? LIMIT 1";
        return jdbcTemplate.query(sql, DataClassRowMapper.newInstance(User.class), id).stream().findFirst();
    }

    public Optional<User> findByEmail(String email) {
        // Logic to find a user by email
        String sql = "SELECT * FROM users WHERE email = ? LIMIT 1";
        return jdbcTemplate.query(sql, DataClassRowMapper.newInstance(User.class), email).stream().findFirst();
    }
}
