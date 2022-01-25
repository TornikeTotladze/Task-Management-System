package com.example.taskmanagementsystem;

import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repository.RoleRepository;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        RoleRepository roleRepository){
        return args -> {
            Role role1 = new Role("Owner",true,true,true,true);
            Role role2 = new Role("Developer",true,false,true,true);
            Role role3 = new Role("Tester", true, false, false,false);
            roleRepository.saveAll(List.of(role1,role2,role3));
//            User alex = new User("Alex",roleRepository.getRoleByRoleId(3l));
//            User bob = new User("Bob",roleRepository.getRoleByRoleId(2l));
//            User tom = new User("Tom",roleRepository.getRoleByRoleId(1l));
//            userRepository.saveAll(List.of(alex, bob, tom));
        };
    }
}
