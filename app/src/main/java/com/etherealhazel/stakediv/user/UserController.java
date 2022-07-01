package com.etherealhazel.stakediv.user;

import java.util.List;
import java.util.UUID;

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
@RequestMapping("/api/user")
public class UserController {
    
    final UserService userService;
 
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> create(@RequestBody AppUser newUser){
        AppUser user = userService.createUser(newUser);
        return user != null ?
            new ResponseEntity<UserDto>(new UserDto(user.getUsername(),
                user.getFirstName()), HttpStatus.CREATED) :
            new ResponseEntity<UserDto>(HttpStatus.CONFLICT);
        
        
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

    @GetMapping("/{uuid}")
    public HttpEntity<AppUser> getUser(@PathVariable("uuid") UUID userId) {
        AppUser user = userService.getUser(userId);
        return user != null ? 
            new ResponseEntity<AppUser>(user, HttpStatus.OK) :
            new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
    }
   
}
