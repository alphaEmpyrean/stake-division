package com.etherealhazel.stakediv.controller;

import java.util.List;
import java.util.UUID;

import com.etherealhazel.stakediv.dto.ContainerDto;
import com.etherealhazel.stakediv.model.Container;
import com.etherealhazel.stakediv.service.ContainerService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpEntity<List<Container>> getAllContainers() {
        List<Container> containers = containerService.getAllContainers();
        return new HttpEntity<>(containers);        
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllContainers() {
        containerService.deleteAllContainers();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Container> getContainer(@PathVariable("uuid") UUID containerId) {
        Container container = containerService.getContainer(containerId);
        if (container != null) {return new ResponseEntity<Container>(container, HttpStatus.OK);}
        else{ return new ResponseEntity<Container>(HttpStatus.NOT_FOUND);}
    }
}
