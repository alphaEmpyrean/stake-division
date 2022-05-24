package com.etherealhazel.stakediv.service;

import com.etherealhazel.stakediv.model.Container;
import com.etherealhazel.stakediv.repo.ContainerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContainerService {

    final ContainerRepository containerRepository;

    @Autowired
    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public void createContainer(Container container) {
        containerRepository.save(container);
    }

}
