package com.example.taskmanagementsystem;

import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repository.RoleRepository;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        TaskRepository taskRepository,
                                        RoleRepository roleRepository){
        return args -> {
//            User alex = new User("Alex","developer");
//            User levan = new User("Levan","developer");
//            User vano = new User("Vano","Owner");
//            userRepository.saveAll(List.of(alex,levan, vano));
//            Role role1 = new Role("Owner",true,true,true,true);
//            Role role2 = new Role("Developer",true,false,true,true);
//            roleRepository.saveAll(List.of(role1,role2));
//            System.out.println(roleRepository.findAll());
        };
    }
}
