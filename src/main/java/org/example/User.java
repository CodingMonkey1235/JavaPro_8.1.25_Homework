package org.example;

import org.springframework.stereotype.Component;

public class User {
    private Long id;
    private String username;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d,username='%s'}",id, username);
    }
}
