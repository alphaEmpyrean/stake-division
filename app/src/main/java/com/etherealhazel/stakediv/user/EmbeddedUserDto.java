package com.etherealhazel.stakediv.user;

import java.util.UUID;

public class EmbeddedUserDto {

        
    public EmbeddedUserDto(String username, UUID id) {
        this.username = username;
        this.id = id;
    }

    private String username;

    private UUID id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    
}
