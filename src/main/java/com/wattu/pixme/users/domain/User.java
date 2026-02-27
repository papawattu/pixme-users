package com.wattu.pixme.users.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record User(
    String id,
    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    String name,
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    @Size(max = 100, message = "Email must be at most 100 characters")
    String email,
    @Size(max = 20, message = "Role must be at most 20 characters")
    String role
) {
    /** Factory method for creating a new User with a generated ID and default role. */
    public static User create(String name, String email) {
        return new UserBuilder().setName(name).setEmail(email).build();
    }
}
