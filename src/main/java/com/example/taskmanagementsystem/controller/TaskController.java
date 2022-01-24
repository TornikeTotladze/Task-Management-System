package com.example.taskmanagementsystem.controller;


import com.example.taskmanagementsystem.Model.Dto.TaskDto;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @PostMapping("/task")
    public void addTask(@RequestBody TaskDto task){
        System.out.println(task);
        taskService.addTask(task);
    }
}
