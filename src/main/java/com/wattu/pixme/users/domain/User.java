package com.wattu.pixme.users.domain;

import java.util.UUID;

public record User(UUID id, String name, String email) {
}
