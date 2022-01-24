package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.Model.Dto.TaskDto;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public void addTask(TaskDto taskDto){
        User currentUser = userRepository.getById(taskDto.getCurrentUserId());
        System.out.println("wants to attach to "+currentUser);
        if(taskDto.getDescription() == null){
            System.out.println(taskRepository.save(new Task(currentUser ,taskDto.getName())));
        } else {
            System.out.println(taskRepository.save(new Task(currentUser ,taskDto.getName(), taskDto.getDescription())));
        }
    }
}
