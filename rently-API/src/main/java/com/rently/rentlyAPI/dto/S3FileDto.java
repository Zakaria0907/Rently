package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.enums.FileType;
import com.rently.rentlyAPI.entity.S3File;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class S3FileDto {
	
	private Integer id;
	
	private String description;
	
	@NotBlank(message = "The file name is required")
	private String filename;
	
	private FileType fileType;
	
	@NotBlank(message = "The file url is required")
	private String storedUrl;
	
	// Method to convert from DTO to entity
	public static S3File fromDTO(S3FileDto dto) {
		S3File s3File = new S3File();
		s3File.setId(dto.getId());
		s3File.setDescription(dto.getDescription());
		s3File.setFilename(dto.getFilename());
		s3File.setFileType(dto.getFileType());
		s3File.setStoredUrl(dto.getStoredUrl());
		return s3File;
	}
	
	// Method to convert entity to DTO
	public static S3FileDto toDTO(S3File s3File) {
		return S3FileDto.builder()
				.id(s3File.getId())
				.description(s3File.getDescription())
				.filename(s3File.getFilename())
				.fileType(s3File.getFileType())
				.storedUrl(s3File.getStoredUrl())
				.build();
	}
}
