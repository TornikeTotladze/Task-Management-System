package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long>{

    public boolean existsByRoleName(String roleName);
}
