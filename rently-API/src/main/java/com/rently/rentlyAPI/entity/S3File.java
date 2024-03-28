package com.rently.rentlyAPI.entity;
import com.rently.rentlyAPI.entity.enums.FileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "s3_file")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class S3File extends AbstractEntity {
	
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
