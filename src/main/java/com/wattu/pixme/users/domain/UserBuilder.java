package com.wattu.pixme.users.domain;

import java.util.UUID;

public class UserBuilder {

    private String email;
    private String name;

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public User build() {
        return new User(UUID.randomUUID().toString(), name, email);
    }
}
