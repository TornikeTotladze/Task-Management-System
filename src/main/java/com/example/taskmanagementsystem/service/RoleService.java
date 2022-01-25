package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.Model.Exception.AlreadyExistsException;
import com.example.taskmanagementsystem.Model.Exception.MissedFieldException;
import com.example.taskmanagementsystem.Model.Exception.NotExist;
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

    public List<Role> getRoles() throws NotExist {
        List<Role> result = roleRepository.findAll();
        if (result.isEmpty()) {
            throw new NotExist("No roles yet");
        }
        return result;
    }

    public void addRole(Role role) throws MissedFieldException, AlreadyExistsException {
        if (role.getRoleName() == null) {
            throw new MissedFieldException("Specify role name");
        }
        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new AlreadyExistsException("Role with this name already exists");
        }
        roleRepository.save(role);
    }

    public void removeRole(Long id) throws NotExist {
        if (!roleRepository.existsById(id)) {
            throw new NotExist("No role with this id");
        }
        roleRepository.deleteById(id);
    }
}
