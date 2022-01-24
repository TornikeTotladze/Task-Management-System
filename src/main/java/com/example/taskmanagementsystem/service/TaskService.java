package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.Model.Dto.TaskDto;
import com.example.taskmanagementsystem.Model.Exception.MissedFieldException;
import com.example.taskmanagementsystem.Model.Exception.NotExist;
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

    public List<Task> getTasks() throws NotExist {
        List<Task> result = taskRepository.findAll();
        if(result.isEmpty()){
            throw new NotExist("No tasks yet");
        }
        return result;
    }

    public void addTask(TaskDto taskDto) throws MissedFieldException, NotExist {
        if(taskDto.getName() == null || taskDto.getCurrentUserId() == null){
            throw new MissedFieldException("Missed necessary fields");
        }
        if(!userRepository.existsById(taskDto.getCurrentUserId())){
            throw new NotExist("User with this id doesn't exist");
        }
        User currentUser = userRepository.getById(taskDto.getCurrentUserId());
        taskRepository.save(new Task(currentUser, taskDto.getName(), taskDto.getDescription()));
    }
}
