package com.etherealhazel.stakediv.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", columnDefinition = "uuid")
    private UUID userID;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @ManyToMany
    @JoinTable(name = "APP_USER_CONTAINER",
        joinColumns = @JoinColumn(name = "USER_ID"), 
        inverseJoinColumns = @JoinColumn(name = "CONTAINER_ID"))
    private List<Container> containers;

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

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

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
}
