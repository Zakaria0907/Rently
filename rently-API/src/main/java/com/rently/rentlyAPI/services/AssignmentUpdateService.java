package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.AssignmentUpdate;
import com.rently.rentlyAPI.entity.EmployeeAssignment;

public interface AssignmentUpdateService {
    AssignmentUpdate createAssignmentUpdateOnCreateAssignment(EmployeeAssignment employeeAssignment);

    void save(AssignmentUpdate assignmentUpdate);

    AssignmentUpdate createAssignmentUpdateOnAssign(EmployeeAssignment employeeAssignment);
}
