package com.etherealhazel.stakediv.service;

import java.util.List;

import com.etherealhazel.stakediv.model.Container;
import com.etherealhazel.stakediv.repo.ContainerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContainerService {

    final ContainerRepository containerRepository;

    @Autowired
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

}
