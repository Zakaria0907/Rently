package com.rently.rentlyAPI.entity.enums;

public enum AssignmentStatus {
    NOT_ASSIGNED, //Means that the assignment is not assigned to an employee yet
    ASSIGNED, //Means that the assignment is assigned to an employee and not started yet
    IN_PROGRESS, //Means that the assignment is in progress
    COMPLETED, //Means that the assignment is completed
    CANCELLED //Means that the assignment is cancelled
}
