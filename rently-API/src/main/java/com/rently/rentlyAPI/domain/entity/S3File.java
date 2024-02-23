package com.rently.rentlyAPI.domain.entity;
import com.rently.rentlyAPI.domain.enums.FIleType;

public class S3File {
	
	private String description;
	private String filename;
	private FIleType fileType;
	
	public S3File(String description, String filename, FIleType fileType) {
		this.description = description;
		this.filename = filename;
		this.fileType = fileType;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public FIleType getFileType() {
		return fileType;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void setFileType(FIleType fileType) {
		this.fileType = fileType;
	}
}
