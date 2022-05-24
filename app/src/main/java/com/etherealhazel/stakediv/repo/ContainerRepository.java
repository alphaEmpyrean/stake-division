package com.etherealhazel.stakediv.repo;

import java.util.UUID;

import com.etherealhazel.stakediv.model.Container;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<Container, UUID>{
    
}
