package com.etherealhazel.stakediv.controller;

import java.util.List;

import com.etherealhazel.stakediv.dto.UserDto;
import com.etherealhazel.stakediv.model.AppUser;
import com.etherealhazel.stakediv.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    final UserService userService;
 
    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<UserDto> create(@RequestBody AppUser user){
        
        userService.createUser(user);
        return new HttpEntity<UserDto>(
            new UserDto(user.getUsername(), user.getFirstName()));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = userService.getAllUsers();
        return new HttpEntity<List<AppUser>>(users);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllUsers() {
        userService.deleteAllUsers();        
    }
   
}
