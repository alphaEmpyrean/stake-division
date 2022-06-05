package com.etherealhazel.stakediv.dto;

import java.util.UUID;

public class EmbeddedContainerDto {        

    public EmbeddedContainerDto(String name, UUID containerId) {
        this.name = name;
        this.containerId = containerId;
    }

    private String name;

    private UUID containerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getContainerId() {
        return containerId;
    }

    public void setContainerId(UUID containerId) {
        this.containerId = containerId;
    }

}
