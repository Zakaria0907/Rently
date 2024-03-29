package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.EmployeeAssignment;
import com.rently.rentlyAPI.entity.enums.AssignmentStatus;
import com.rently.rentlyAPI.entity.enums.WorkType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class EmployeeAssignmentDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("company_id")
    private Integer companyId;
    @JsonProperty("employee_id")
    private Integer employeeId;
    @JsonProperty("owner_request_id")
    private Integer ownerRequestId;
    @JsonProperty("work_type")
    private String workType;

    @JsonProperty("status")
    private String status;
    @JsonProperty("assignment_updates")
    private List<AssignmentUpdateDto> assignmentUpdates;


    public static EmployeeAssignmentDto fromEntity(EmployeeAssignment employeeAssignment) {
        EmployeeAssignmentDtoBuilder dtoBuilder = EmployeeAssignmentDto.builder()
                .id(employeeAssignment.getId())
                .companyId(employeeAssignment.getCompany().getId())
                .workType(employeeAssignment.getWorkType().name())
                .status(employeeAssignment.getStatus().name())
                .ownerRequestId(employeeAssignment.getOwnerRequest().getId())
                .assignmentUpdates(employeeAssignment.getUpdates()
                        .stream()
                        .map(AssignmentUpdateDto::fromEntity)
                        .collect(Collectors.toList()));

        if (employeeAssignment.getEmployee() != null) {
            dtoBuilder.employeeId(employeeAssignment.getEmployee().getId());
        }

        return dtoBuilder.build();
    }


    public static EmployeeAssignment toEntity(EmployeeAssignmentDto employeeAssignmentDto) {
        return EmployeeAssignment.builder()
                .id(employeeAssignmentDto.getId())
                .workType(WorkType.valueOf(employeeAssignmentDto.getWorkType()))
                .status(AssignmentStatus.valueOf(employeeAssignmentDto.getStatus()))
                .build();
    }
}
