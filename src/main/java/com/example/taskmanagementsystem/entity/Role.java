package com.example.taskmanagementsystem.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Setter
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    /** Permissions */
    @Column(name = "task_see")
    private Boolean taskSeePerm;

    @Column(name = "task_create")
    private Boolean taskCreatePerm;

    @Column(name = "task_edit")
    private Boolean taskEditPerm;

    @Column(name = "task_delete")
    private Boolean taskDeletePerm;
    /** end of Permissions */

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "role")
    @JsonIgnore
    private List<User> users;

    public Role() {
    }

    public Role(String roleName, Boolean taskSeePerm,
                Boolean taskCreatePerm, Boolean taskEditPerm,
                Boolean tasDeletePerm) {
        this.roleName = roleName;
        this.taskSeePerm = taskSeePerm;
        this.taskCreatePerm = taskCreatePerm;
        this.taskEditPerm = taskEditPerm;
        this.taskDeletePerm = tasDeletePerm;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", taskSeePerm=" + taskSeePerm +
                ", taskCreatePerm=" + taskCreatePerm +
                ", taskEditPerm=" + taskEditPerm +
                ", tasDeletePerm=" + taskDeletePerm +
                '}';
    }
}
