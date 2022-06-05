package com.etherealhazel.stakediv.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.etherealhazel.stakediv.serializer.EmbeddedUserSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Container {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTAINER_ID", columnDefinition = "uuid")
    private UUID containerId;

    @Column(name = "NAME")
    private String name;

    @JsonSerialize(using = EmbeddedUserSerializer.class)
    @ManyToMany
    @JoinTable(name = "CONTAINER_APP_USER", 
        joinColumns = @JoinColumn(name = "CONTAINER_ID"), 
        inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<AppUser> users;

    public UUID getContainerId() {
        return containerId;
    }

    public void setContainerId(UUID containerId) {
        this.containerId = containerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }  
    
}
