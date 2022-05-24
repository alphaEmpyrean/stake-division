package com.etherealhazel.stakediv.controller;

import com.etherealhazel.stakediv.dto.ContainerDto;
import com.etherealhazel.stakediv.model.Container;
import com.etherealhazel.stakediv.service.ContainerService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/container")
public class ContainerController {
    
    final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<ContainerDto> createContainer(@RequestBody Container container) {
        containerService.createContainer(container);
        return new HttpEntity<ContainerDto>(new ContainerDto(container.getName()));

    }
}
