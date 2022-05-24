package com.etherealhazel.stakediv.repo;

import java.util.Optional;
import java.util.UUID;

import com.etherealhazel.stakediv.model.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {

    public Optional<AppUser> findByUsername(String username);    
}
