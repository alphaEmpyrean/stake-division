package com.etherealhazel.stakediv.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.etherealhazel.stakediv.serializer.EmbeddedContainerSerializer;
import com.etherealhazel.stakediv.serializer.EmbeddedUserSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Container {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTAINER_ID", columnDefinition = "uuid")
    private UUID uuid;

    @Column(name = "NAME")
    private String name;

    @JsonSerialize(using = EmbeddedUserSerializer.class)
    @ManyToMany
    @JoinTable(name = "CONTAINER_APP_USER", 
        joinColumns = @JoinColumn(name = "CONTAINER_ID"), 
        inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<AppUser> users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_CONTAINER")
    private Container parentContainer;

    @JsonSerialize(using = EmbeddedContainerSerializer.class)
    @OneToMany(mappedBy = "parentContainer", fetch = FetchType.EAGER)
    private List<Container> childContainers;


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }

    public Container getParentContainer() {
        return parentContainer;
    }

    public void setParentContainer(Container parentContainer) {
        this.parentContainer = parentContainer;
    }

    public List<Container> getChildContainers() {
        return childContainers;
    }

    public void setChildContainers(List<Container> childContainers) {
        this.childContainers = childContainers;
    }    
}
