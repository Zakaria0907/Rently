package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(path = "/assignments")
    public ResponseEntity<List<EmployeeAssignmentDto>> getAssignments(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(employeeService.getAllAssignments(token));
    }

    @GetMapping(path = "/assignments/id={id}")
    public ResponseEntity<EmployeeAssignmentDto> getAssignmentById(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getAssignmentById(token, id));
    }
}
