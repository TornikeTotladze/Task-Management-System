package com.example.taskmanagementsystem.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private String userName;
    private Long roleId;

    public UserDto() {
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
