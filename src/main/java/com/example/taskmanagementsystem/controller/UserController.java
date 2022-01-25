package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.Model.Dto.UserDto;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> result = this.userService.getUsers();
        System.out.println(result);
        return result;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody UserDto userDto){
        log.info(userDto.toString());
        userService.addUser(userDto);
    }
}
