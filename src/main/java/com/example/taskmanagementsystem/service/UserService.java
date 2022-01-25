package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.Model.Dto.UserDto;
import com.example.taskmanagementsystem.Model.Exception.MissedFieldException;
import com.example.taskmanagementsystem.Model.Exception.NotExist;
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

    public void addRole(UserDto userDto) throws MissedFieldException, NotExist {
        if (userDto.getUserName() == null) {
            throw new MissedFieldException("User name isn't specified");
        }
        if (userDto.getRoleId() == null) {
            throw new MissedFieldException("User role id isn't specified");
        }
        if (!roleRepository.existsById(userDto.getRoleId())) {
            throw new NotExist("Role with this id doesn't exist");
        }
        Role role = roleRepository.getRoleByRoleId(userDto.getRoleId());
        userRepository.save(new User(userDto.getUserName(), role));
    }

    public List<User> getUsers() throws NotExist {
        List<User> result = userRepository.findAll();
        if (result.isEmpty()) {
            throw new NotExist("No users yet");
        }
        return result;
    }

    public User getUser(Long userId) throws NotExist {
        if (!userRepository.existsById(userId)) {
            throw new NotExist("User with this id doesn't exist");
        }
        return userRepository.getUserByUserId(userId);
    }

    public void deleteUser(Long userId) throws NotExist {
        if (!userRepository.existsById(userId)) {
            throw new NotExist("User with this id doesn't exist");
        }
        userRepository.deleteById(userId);
    }
}
