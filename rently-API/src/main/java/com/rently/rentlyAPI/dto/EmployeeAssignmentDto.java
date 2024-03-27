package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.EmployeeAssignment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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


    public static EmployeeAssignmentDto fromEntity(EmployeeAssignment employeeAssignment) {
        EmployeeAssignmentDtoBuilder dtoBuilder = EmployeeAssignmentDto.builder()
                .id(employeeAssignment.getId())
                .companyId(employeeAssignment.getCompany().getId())
                .ownerRequestId(employeeAssignment.getOwnerRequest().getId());

        if (employeeAssignment.getEmployee() != null) {
            dtoBuilder.employeeId(employeeAssignment.getEmployee().getId());
        }

        return dtoBuilder.build();
    }


    public static EmployeeAssignment toEntity(EmployeeAssignmentDto employeeAssignmentDto) {
        return EmployeeAssignment.builder()
                .id(employeeAssignmentDto.getId())
                .build();
    }
}
