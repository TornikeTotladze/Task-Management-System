package com.example.taskmanagementsystem.controller;


import com.example.taskmanagementsystem.Model.Dto.TaskDto;
import com.example.taskmanagementsystem.Model.Exception.MissedFieldException;
import com.example.taskmanagementsystem.Model.Exception.NotExist;
import com.example.taskmanagementsystem.Model.Exception.NotHavePermission;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
    public ResponseEntity<List<Task>> getTasks(@RequestParam(name = "user-id") Long userId) {
        try {
            return new ResponseEntity<>(taskService.getTasks(userId), HttpStatus.ACCEPTED); // status code!!
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST); // status code!!
        } catch (NotHavePermission e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST); // status code!!
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST); // status code!!
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id, @RequestParam(name = "user-id") Long userId) {
        try {
            return new ResponseEntity<>(taskService.getTask(id, userId), HttpStatus.OK);
        } catch (MissedFieldException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (NotHavePermission e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/task")
    public ResponseEntity<String> addTask(@RequestBody TaskDto task, @RequestParam(name = "user-id") Long userId) {
        try {
            taskService.addTask(task, userId);
            return new ResponseEntity<>("Task added successfully", HttpStatus.ACCEPTED); // status code!!
        } catch (MissedFieldException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // status code!!
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT); // status code!!
        } catch (NotHavePermission e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT); // status code!!
        }
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable(name = "id") Long taskId,
                                             @RequestParam(name = "user-id") Long userId) {
        try {
            taskService.deleteTask(taskId, userId);
            return new ResponseEntity<>("Task deleted successfully", HttpStatus.ACCEPTED); // status code!!
        } catch (MissedFieldException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // status code!!
        } catch (NotHavePermission e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // status code!!
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // status code!!
        }
    }

    @PutMapping("/edit-task/{id}")
    public ResponseEntity<String> taskEditChangeName(@RequestBody String newName, @PathVariable String id) {

    }
}

//    @RequestParam Long Id