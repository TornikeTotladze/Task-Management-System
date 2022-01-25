package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task getTaskByTaskId(Long taskId);

    @Modifying
    @Query("update Task t set t.taskName = :new_name where t.taskId = :id")
    void changeTaskName(@Param("new_name") String newName, @Param("id") Long taskId);

    @Modifying
    @Query("update Task t set t.shortDescription = :new_desc where t.taskId = :id")
    void changeTaskDescription(@Param("new_desc") String newDesc, @Param("id") Long taskId);

}
