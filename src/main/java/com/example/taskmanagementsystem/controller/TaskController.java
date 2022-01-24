package com.example.taskmanagementsystem.controller;


import com.example.taskmanagementsystem.Model.Dto.TaskDto;
import com.example.taskmanagementsystem.Model.Exception.MissedFieldException;
import com.example.taskmanagementsystem.Model.Exception.NotExist;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Task> > getTasks(){
        try {
            return new ResponseEntity<>(taskService.getTasks(), HttpStatus.ACCEPTED); // status code!!
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); // status code!!
        }
    }


    @PostMapping("/task")
    public ResponseEntity<String> addTask(@RequestBody TaskDto task){
        try {
            taskService.addTask(task);
            return new ResponseEntity<>("Task added successfully", HttpStatus.ACCEPTED); // status code!!
        } catch (MissedFieldException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Enter task name and user id", HttpStatus.CONFLICT); // status code!!
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>("User with this id doesn't exist", HttpStatus.NO_CONTENT); // status code!!
        }
    }

}
