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
import org.springframework.web.bind.annotation.PatchMapping;
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
    public HttpEntity<Container> createContainer(@RequestBody Container container) {
        return new HttpEntity<>(containerService.createContainer(container));
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
        return container != null ? 
            new ResponseEntity<>(container, HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{parentUuid}/childContainer/{childUuid}")
    public ResponseEntity<ContainerDto> addChildContainer(@PathVariable("parentUuid") UUID parentId, @PathVariable("childUuid") UUID childId) {
        Container container = containerService.addChildContainer(parentId, childId);

        return container != null ? 
            new ResponseEntity<ContainerDto>(new ContainerDto(container.getName()), HttpStatus.OK) :
            new ResponseEntity<ContainerDto>(HttpStatus.NOT_FOUND);
    }
}
