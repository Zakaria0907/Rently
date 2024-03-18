package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.EmploymentContract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmploymentContractDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("company_id")
    private Integer companyId;
    @JsonProperty("building_id")
    private Integer buildingId;
    @JsonProperty("employee_id")
    private Integer employeeId;
    @JsonProperty("yearly_salary")
    private long yearlySalary;
    @JsonProperty("start_date")
    private String startDate;

    public static EmploymentContractDto fromEntity(EmploymentContract employmentContract) {
        return EmploymentContractDto.builder()
                .id(employmentContract.getId())
                .companyId(employmentContract.getCompany().getId())
                .buildingId(employmentContract.getBuilding().getId())
                .employeeId(employmentContract.getEmployee().getId())
                .yearlySalary(employmentContract.getYearlySalary())
                .startDate(employmentContract.getStartDate())
                .build();
    }

    public static EmploymentContract toEntity(EmploymentContractDto employmentContractDto) {
        return EmploymentContract.builder()
                .id(employmentContractDto.getId())
                .yearlySalary(employmentContractDto.getYearlySalary())
                .startDate(employmentContractDto.getStartDate())
                .build();
    }


}
