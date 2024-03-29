package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.AssignmentUpdate;
import com.rently.rentlyAPI.entity.enums.AssignmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class AssignmentUpdateDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("change_date")
    private LocalDateTime creationDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("comment")
    private String comment;

    public static AssignmentUpdateDto fromEntity(AssignmentUpdate assignmentUpdate) {
        return AssignmentUpdateDto.builder()
                .id(assignmentUpdate.getId())
                .creationDate(assignmentUpdate.getCreationDate())
                .status(assignmentUpdate.getStatus().name())
                .comment(assignmentUpdate.getComment())
                .build();
    }

    public static AssignmentUpdate toEntity(AssignmentUpdateDto assignmentUpdateDto) {
        return AssignmentUpdate.builder()
                .status(AssignmentStatus.valueOf(assignmentUpdateDto.getStatus()))
                .comment(assignmentUpdateDto.getComment())
                .build();
    }

}
