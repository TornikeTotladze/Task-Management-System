package com.example.taskmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "currentUser")
    @JsonIgnore
    private List<Task> assignedTasks;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "creatorUser")
    @JsonIgnore
    private List<Task> createdTasks;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(){}

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + userId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
