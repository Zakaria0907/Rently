package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.CondoFile;
import com.rently.rentlyAPI.entity.S3File;
import com.rently.rentlyAPI.entity.enums.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondoFileRepository extends JpaRepository<CondoFile, Integer> {
	
	// Example: Find by filename
	CondoFile findByFilename(String filename);
	
	// Example: Find by FileType
	List<CondoFile> findByFileType(FileType fileType);
}
