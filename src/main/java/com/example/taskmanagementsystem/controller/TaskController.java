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

    @GetMapping("/task")
    public ResponseEntity<List<Task>> getTasks(@RequestParam(name = "user-id") Long userId) {
        try {
            return new ResponseEntity<>(taskService.getTasks(userId), HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        } catch (NotHavePermission e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/task/task-id={task-id}")
    public ResponseEntity<Task> getTask(@PathVariable(name = "task-id") Long taskId,
                                        @RequestParam(name = "user-id") Long userId) {
        try {
            return new ResponseEntity<>(taskService.getTask(taskId, userId), HttpStatus.OK);
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
    public ResponseEntity<String> addTask(@RequestBody TaskDto task,
                                          @RequestParam(name = "user-id") Long userId) {
        try {
            taskService.addTask(task, userId);
            return new ResponseEntity<>("Task added successfully", HttpStatus.ACCEPTED);
        } catch (MissedFieldException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (NotHavePermission e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete-task/task-id={task-id}")
    public ResponseEntity<String> deleteTask(@PathVariable(name = "task-id") Long taskId,
                                             @RequestParam(name = "user-id") Long userId) {
        try {
            taskService.deleteTask(taskId, userId);
            return new ResponseEntity<>("Task deleted successfully", HttpStatus.ACCEPTED);
        } catch (MissedFieldException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotHavePermission e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit-task/task-id={task-id}")
    public ResponseEntity<String> taskEdit(@RequestBody TaskDto taskDto,
                                           @PathVariable(name = "task-id") Long taskId,
                                           @RequestParam(name = "user-id") Long userId) {
        System.out.println("taskId: " + taskId + " userId: " + userId);
        try {
            taskService.editTask(taskDto, taskId, userId);
            return new ResponseEntity<>("Task name edited successfully", HttpStatus.ACCEPTED);
        } catch (MissedFieldException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotHavePermission e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotExist e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
