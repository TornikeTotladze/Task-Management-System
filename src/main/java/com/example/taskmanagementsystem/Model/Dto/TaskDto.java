package com.example.taskmanagementsystem.Model.Dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskDto {

    private String name;
    private String description;
    private Long currentUserId;

    public TaskDto(){}

    public TaskDto(String name, String description, Long currentUserId) {
        this.name = name;
        this.description = description;
        this.currentUserId = currentUserId;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + currentUserId +
                '}';
    }
}
