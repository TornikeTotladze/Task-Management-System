package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @PostMapping("/role")
    public ResponseEntity<String> addRole(@RequestBody Role role){
        if(roleService.addRole(role)){
            return new ResponseEntity<>("Role saved successfully!", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Role with this name already exists", HttpStatus.BAD_REQUEST);
    }
}
