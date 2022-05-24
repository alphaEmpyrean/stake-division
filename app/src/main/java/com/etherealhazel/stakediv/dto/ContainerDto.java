package com.etherealhazel.stakediv.dto;

import java.util.UUID;

public class ContainerDto {
    
    public ContainerDto() {
    }
    
    public ContainerDto(String name) {
        this.name = name;
    }

    private UUID containerId;

    private String name;

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
}
