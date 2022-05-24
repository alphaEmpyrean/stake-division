package com.etherealhazel.stakediv.dto;

public class ContainerDto {
    
    public ContainerDto() {
    }
    
    public ContainerDto(String name) {
        this.name = name;
    }
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
