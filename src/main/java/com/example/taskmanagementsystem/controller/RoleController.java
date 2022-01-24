package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.Model.Exception.AlreadyExistsException;
import com.example.taskmanagementsystem.Model.Exception.MissedFieldException;
import com.example.taskmanagementsystem.Model.Exception.NotExist;
import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Role> > getRoles(){
        try {
            return new ResponseEntity<>(roleService.getRoles(), HttpStatus.ACCEPTED);// statusCode!!
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);// statusCode!!
        }
    }

    @PostMapping("/role")
    public ResponseEntity<String> addRole(@RequestBody Role role){
        try {
            roleService.addRole(role);
            return new ResponseEntity<>("Role saved successfully!", HttpStatus.ACCEPTED); // statusCode!!
        } catch (MissedFieldException e){
            e.printStackTrace();
            return new ResponseEntity<>("Enter role name", HttpStatus.CONFLICT); // statusCode!!
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Role with this name already exists", HttpStatus.CONFLICT); // statusCode!!
        }
    }

    @DeleteMapping("/remove-role/{id}")
    public ResponseEntity<String> removeRole(@PathVariable Long id){
        try {
            roleService.removeRole(id);
            return new ResponseEntity<>("Removed successfully", HttpStatus.ACCEPTED);// statusCode!!
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>("No role with this id", HttpStatus.NO_CONTENT);// statusCode!!
        }
    }
}
