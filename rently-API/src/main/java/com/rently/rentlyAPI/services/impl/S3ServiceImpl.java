package com.rently.rentlyAPI.services.impl;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rently.rentlyAPI.entity.enums.FileType;
import com.rently.rentlyAPI.entity.S3File;

import com.rently.rentlyAPI.entity.user.User;
import com.rently.rentlyAPI.exceptions.FileUploadException;
import com.rently.rentlyAPI.repository.S3FileRepository;
import com.rently.rentlyAPI.repository.UserRepository;
import com.rently.rentlyAPI.services.S3Service;
import com.rently.rentlyAPI.services.UserService;
import com.rently.rentlyAPI.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;

@Service
public class S3ServiceImpl implements S3Service {

	@Autowired
	private S3FileRepository s3FileRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtils jwtUtils;

	private String bucketName = "rently-bucket-0";
	Regions region = Regions.CA_CENTRAL_1;

	@Value("${AWS_KEY}")
	private String accessKey;

	@Value("${AWS_SECRET}")
	private String secretKey;

	private AmazonS3 s3Client;

	//getS3Client method is used to get the S3 client.
	private AmazonS3 getS3Client() {
		if (s3Client == null) {
			s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
					.withRegion(region)
					.build();
		}
		return s3Client;
	}

	//TODO: Make proper custom exception handling
	//uploadImage method is used to upload the image to the S3 bucket and save the file to the database.
	@Override
	public void uploadImage(MultipartFile imageFile, String token) {

		try {
			User user = userService.findUserAccordingToTypeWithEmail(jwtUtils.extractUsername(token));

			// If profile picture existe delete from db and s3
			Optional<S3File> profilePicture = Optional.ofNullable(user.getProfilePicture());
			if (profilePicture.isPresent()) {
				String key = profilePicture.get().getFilename();
				user.setProfilePicture(null);
				userRepository.save(user);
				getS3Client().deleteObject(bucketName, key);
				s3FileRepository.delete(profilePicture.get());
			}

			String fileName = imageFile.getOriginalFilename();
			String description = user.getFirstName() + " " + user.getLastName() + " has " + fileName + " as a picture";
			assert fileName != null;
			FileType fileType = determineFileType(fileName);
			String storedUrl = uploadFileToS3(imageFile);

			// Save file to database
			S3File s3File = new S3File(description, fileName, fileType, storedUrl);
			s3FileRepository.save(s3File);

			// Update user profile picture
			user.setProfilePicture(s3File);
			userRepository.save(user);

		} catch (SdkClientException e) {
			throw FileUploadException.wrap(e);
		}
	}

	//uploadFileToS3 method is used to upload the file to the S3 bucket and return the URL of the file.
	private String uploadFileToS3(MultipartFile file)  {

		try {
			InputStream inputStream = file.getInputStream();
			String filename = file.getOriginalFilename();

			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(file.getContentType());
			objectMetadata.setContentLength(inputStream.available());

			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filename, inputStream, objectMetadata);
			getS3Client().putObject(putObjectRequest);

			return getS3Client().getUrl(bucketName, filename).toExternalForm();

		} catch (SdkClientException | IOException e) {
			throw new FileUploadException("Failed to upload file", e);
		}
	}
	//determineFileType method is used to determine the file type based on the file extension.
	private FileType determineFileType(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
		return Arrays.stream(FileType.values())
				.filter(ft -> ft.name().equals(extension))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unsupported file type: " + extension));
	}

}
