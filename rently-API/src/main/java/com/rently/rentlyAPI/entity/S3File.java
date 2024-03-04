package com.rently.rentlyAPI.entity;
import com.rently.rentlyAPI.entity.enums.FileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "s3_file")
public class S3File extends AbstractEntity {
	
	@Column(length = 1024)
	private String description;
	
	@NotBlank(message = "The file name is required")
	@Column(nullable = false, unique = true)
	private String filename;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FileType fileType;
	
	@Column(length = 2048)
	@NotBlank(message = "The file url is required")
	private String storedUrl;
}
