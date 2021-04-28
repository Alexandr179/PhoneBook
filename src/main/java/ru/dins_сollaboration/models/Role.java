package ru.dins_сollaboration.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    OPERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}