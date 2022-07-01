package com.etherealhazel.stakediv.user;

public class UserDto {

    public UserDto() {
    }
    
    public UserDto(String username, String firstName) {
        this.username = username;
        this.firstName = firstName;
    }

    private String username;

    private String firstName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }    
}
