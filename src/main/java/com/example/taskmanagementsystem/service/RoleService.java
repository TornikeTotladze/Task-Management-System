package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public boolean addRole(Role role){
        if(roleRepository.existsByRoleName(role.getRoleName())){
//            System.out.println("already exists the role with this name");
            return false;
        }
        roleRepository.save(role);
        return true;
    }
}
