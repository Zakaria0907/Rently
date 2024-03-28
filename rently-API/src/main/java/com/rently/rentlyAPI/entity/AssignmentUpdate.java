package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.enums.AssignmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assignment_update")
public class AssignmentUpdate extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'NOT_ASSIGNED'")
    private AssignmentStatus status;

    private String comment;

    @ManyToOne
    private EmployeeAssignment employeeAssignment;

}
