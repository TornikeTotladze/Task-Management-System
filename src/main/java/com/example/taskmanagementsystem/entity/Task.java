package com.example.taskmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long taskId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User currentUser;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "task_description", nullable = true)
    private String shortDescription;

    public Task(){}

    public Task(User currentUser, String taskName) {
        this.currentUser = currentUser;
        this.taskName = taskName;
    }

    public Task(User currentUser, String taskName, String shortDescription) {
        this.currentUser = currentUser;
        this.taskName = taskName;
        this.shortDescription = shortDescription;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", current user= "+ currentUser.getName() + '\'' +
                '}';
    }
}
