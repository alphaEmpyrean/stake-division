package com.etherealhazel.stakediv.container;

import java.util.UUID;

public class EmbeddedContainerDto {        

    public EmbeddedContainerDto(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    private String name;

    private UUID uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getuuid() {
        return uuid;
    }

    public void setuuid(UUID uuid) {
        this.uuid = uuid;
    }

}
