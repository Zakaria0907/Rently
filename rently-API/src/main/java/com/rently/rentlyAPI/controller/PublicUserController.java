package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.entity.user.PublicUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PublicUserController {

    @PostMapping(path = "/create/public-user")
    public ResponseEntity<PublicUserDto> createPublicUser(@RequestBody PublicUserDto publicUserDto) {
        return ResponseEntity.ok(userService.registerEmployee(employeeDto));
    }
}
