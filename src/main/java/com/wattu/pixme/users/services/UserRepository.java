package com.wattu.pixme.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wattu.pixme.users.domain.User;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, email, role FROM users ORDER BY name",
                DataClassRowMapper.newInstance(User.class));
    }

    public void save(User user) {
        jdbcTemplate.update(
                "INSERT INTO users (id, name, email, role) VALUES (?, ?, ?, ?)",
                user.id(), user.name(), user.email(), user.role());
    }

    public Optional<User> findById(String id) {
        return jdbcTemplate.query(
                "SELECT id, name, email, role FROM users WHERE id = ? LIMIT 1",
                DataClassRowMapper.newInstance(User.class), id)
                .stream().findFirst();
    }

    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query(
                "SELECT id, name, email, role FROM users WHERE email = ? LIMIT 1",
                DataClassRowMapper.newInstance(User.class), email)
                .stream().findFirst();
    }

    public int deleteById(String id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}
