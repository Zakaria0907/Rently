package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.S3File;
import com.rently.rentlyAPI.entity.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface S3FileRepository extends JpaRepository<S3File, Integer> {
	
	// Example: Find by filename
	S3File findByFilename(String filename);
	
	// Example: Find by FileType
	List<S3File> findByFileType(FileType fileType);
}
