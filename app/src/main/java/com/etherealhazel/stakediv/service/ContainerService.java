package com.etherealhazel.stakediv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.etherealhazel.stakediv.model.Container;
import com.etherealhazel.stakediv.repo.ContainerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContainerService {

    final ContainerRepository containerRepository;

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    @Transactional
    public void createContainer(Container container) {
        containerRepository.save(container);
    }

    public List<Container> getAllContainers() {
        List<Container> containers = containerRepository.findAll();
        return containers;
    }

    @Transactional
    public void deleteAllContainers() {
        containerRepository.deleteAll();
    }

    public Container getContainer(UUID containerId) {
        Optional<Container> oContainer = containerRepository.findById(containerId);
        oContainer.orElse(null);
        Container container = oContainer.isPresent() ? oContainer.get() : null;
        return container;
    }

    @Transactional
    public Container addChildContainer(UUID parentId, UUID childUuid) {
        Container childContainer = getContainer(childUuid);
        Container parentContainer = getContainer(parentId);
        
        List<Container> tempChildList = new ArrayList<>();
        tempChildList.add(childContainer);

        parentContainer.getChildContainers().clear();
        parentContainer.getChildContainers().addAll(tempChildList);
        
        childContainer.setParentContainer(parentContainer);

        List<Container> entities = new ArrayList<>();

        entities.add(childContainer);
        entities.add(parentContainer);

       
        entities = containerRepository.saveAll(entities);

        return entities.get(0);
        
    }

}
