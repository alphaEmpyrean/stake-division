package com.etherealhazel.stakediv.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import com.etherealhazel.stakediv.serializer.EmbeddedContainerSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UUID", columnDefinition = "uuid")
    private UUID uuid;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @JsonSerialize(using = EmbeddedContainerSerializer.class)
    @ManyToMany(mappedBy = "users")   
    private List<Container> containers;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
