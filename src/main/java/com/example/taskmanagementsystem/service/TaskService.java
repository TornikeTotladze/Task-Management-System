package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.Model.Dto.TaskDto;
import com.example.taskmanagementsystem.Model.Exception.MissedFieldException;
import com.example.taskmanagementsystem.Model.Exception.NotExist;
import com.example.taskmanagementsystem.Model.Exception.NotHavePermission;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public List<Task> getTasks(Long userId) throws NotExist, NotHavePermission {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with this id doesn't exist");
        }
        if (!userRepository.userCanSeeTasks(userId)) {
            throw new NotHavePermission("User can't see tasks");
        }
        List<Task> result = taskRepository.findAll();
        if (result.isEmpty()) {
            throw new NotExist("No tasks yet");
        }
        return result;
    }

    public Task getTask(Long taskId, Long userId)
            throws MissedFieldException, NotExist, NotHavePermission {
        if (taskId == null || userId == null) {
            throw new MissedFieldException("ids must not be null");
        }
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with this id doesn't exist");
        }
        if (!userRepository.userCanSeeTasks(userId)) {
            throw new NotHavePermission("User can't see tasks");
        }
        if (!taskRepository.existsById(taskId)) {
            throw new NotExist("Task with this id doesn't exist");
        }
        return taskRepository.getTaskByTaskId(taskId);
    }

    public void addTask(TaskDto taskDto, Long creatorUserId)
            throws MissedFieldException, NotExist, NotHavePermission {
        if (taskDto.getName() == null || creatorUserId == null || taskDto.getCurrentUserId() == null) {
            throw new MissedFieldException("Missed necessary fields");
        }
        if (!userRepository.existsById(taskDto.getCurrentUserId()) ||
                !userRepository.existsById(creatorUserId)) {
            throw new NotExist("User with this id doesn't exist");
        }
        if (!userRepository.userCanCreateTasks(creatorUserId)) {
            throw new NotHavePermission("User Can't create task");
        }
        User creatorUser = userRepository.getById(creatorUserId);
        User currentUser = userRepository.getById(taskDto.getCurrentUserId());
        taskRepository.save(new Task(creatorUser, currentUser, taskDto.getName(), taskDto.getDescription()));
    }

    public void deleteTask(Long taskId, Long userId)
            throws MissedFieldException, NotHavePermission, NotExist {
        if (taskId == null || userId == null) {
            throw new MissedFieldException("ids must not be null");
        }
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with this id doesn't exist");
        }
        if (!userRepository.userCanDeleteTasks(userId)) {
            throw new NotHavePermission("User can't delete tasks");
        }
        if (!taskRepository.existsById(taskId)) {
            throw new NotExist("Task with this id doesn't exist");
        }
        taskRepository.deleteById(taskId);
    }

    public void editTask(TaskDto taskDto, Long taskId, Long userId)
            throws MissedFieldException, NotHavePermission, NotExist {
        if (taskId == null || userId == null) {
            throw new MissedFieldException("ids must not be null");
        }
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with this id doesn't exist");
        }
        if (!userRepository.userCanEditTasks(userId)) {
            throw new NotHavePermission("User can't edit tasks");
        }
        if (!taskRepository.existsById(taskId)) {
            throw new NotExist("Task with this id doesn't exist");
        }
        if (taskDto.getName() != null) {
            taskRepository.changeTaskName(taskDto.getName(), taskId);
        }
        if (taskDto.getDescription() != null) {
            taskRepository.changeTaskDescription(taskDto.getDescription(), taskId);
        }
    }

}
