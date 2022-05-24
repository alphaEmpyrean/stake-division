package com.etherealhazel.stakediv.controller;

import com.etherealhazel.stakediv.dto.UserDto;
import com.etherealhazel.stakediv.model.AppUser;
import com.etherealhazel.stakediv.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
}
