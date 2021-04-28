package ru.dins_сollaboration.models;

import org.springframework.security.core.GrantedAuthority;

public enum State implements GrantedAuthority {
    ALLOWED,
    LOCKED;

    @Override
    public String getAuthority() {
        return name();
    }
}