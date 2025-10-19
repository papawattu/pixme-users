package com.wattu.pixme.users.services;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }
}
