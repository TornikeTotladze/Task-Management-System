package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User getUserByUserId(Long userId);

    @Query("select r.taskSeePerm from User as u inner join Role as r " +
            "on r.roleId = u.role.roleId where u.userId = :userId")
    public boolean userCanSeeTasks(@Param("userId") Long userId);

    @Query("select r.taskCreatePerm from User as u inner join Role as r " +
            "on r.roleId = u.role.roleId where u.userId = :userId")
    public boolean userCanCreateTasks(@Param("userId") Long userId);

    @Query("select r.taskEditPerm from User as u inner join Role as r " +
            "on r.roleId = u.role.roleId where u.userId = :userId")
    public boolean userCanEditTasks(@Param("userId") Long userId);

    @Query("select r.taskDeletePerm from User as u inner join Role as r " +
            "on r.roleId = u.role.roleId where u.userId = :userId")
    public boolean userCanDeleteTasks(@Param("userId") Long userId);

}
