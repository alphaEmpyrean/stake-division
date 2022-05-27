package com.etherealhazel.stakediv.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.etherealhazel.stakediv.model.AppUser;
import com.etherealhazel.stakediv.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public AppUser createUser(AppUser user) {
        return userRepository.findByUsername(user.getUsername()).isEmpty() ? userRepository.save(user) : null;
    }

    public List<AppUser> getAllUsers() {
        List<AppUser> users = userRepository.findAll();
        return users;
    }

    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public AppUser getUser(UUID userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        return user.isPresent() ? user.get() : null;    
    }

}
