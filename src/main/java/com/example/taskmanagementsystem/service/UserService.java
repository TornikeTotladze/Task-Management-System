package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.Model.UserDto;
import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repository.RoleRepository;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(UserDto userDto){
        Role role = roleRepository.getById(userDto.getRoleId());
        userRepository.save(new User(userDto.getUserName(), role));
    }

    public User getUser(Long id){
        if(!userRepository.existsById(id)){
            return null;
        }
        return userRepository.getById(id);
    }
}
