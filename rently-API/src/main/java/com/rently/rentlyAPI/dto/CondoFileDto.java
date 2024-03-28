package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.CondoFile;
import com.rently.rentlyAPI.entity.enums.FileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CondoFileDto {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("file_name")
	@NotBlank(message = "The file name is required")
	private String filename;
	
	@JsonProperty("file_type")
//	@NotBlank(message = "The file type is required")
	private String fileType;
	
	@JsonProperty("stored_url")
	private String storedUrl;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("condo_id")
	private Integer condoId;
	
	public static CondoFile toEntity(CondoFileDto condoFileDto){
		return CondoFile.builder()
				.filename(condoFileDto.getFilename())
				.fileType(FileType.valueOf(condoFileDto.getFileType()))
				// stored url is set in the business logic
				.description(condoFileDto.getDescription())
				.build();
	}
	
	public static CondoFileDto fromEntity(CondoFile condoFile){
		return CondoFileDto.builder()
				.id(condoFile.getId())
				.filename(condoFile.getFilename())
				.fileType(condoFile.getFileType().name())
				.storedUrl(condoFile.getStoredUrl())
				.description(condoFile.getDescription())
				.condoId(condoFile.getCondo().getId())
				.build();
	}
}
