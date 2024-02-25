package com.rently.rentlyAPI.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

@Service
public class S3Service {
	//TODO: Update service to fetch api data, create object, put to amazon, return response and stored url.
	//TODO: Make that service as an Interface and implement it in the service class.
	
	private String bucketName = "rently-bucket-0";
	Regions region = Regions.CA_CENTRAL_1;
	
	
	public String uploadToS3(InputStream inputStream, String filename) throws IOException, AmazonServiceException, SdkClientException {
		
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIA3FLDZBBCC7LXIKEG",
				"VKxMOuI9FNXE2TedMblmcbLC115EWbGX1vNujJlA"
		);
		
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.CA_CENTRAL_1)
				.build();
		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("image/jpeg");
		objectMetadata.setContentLength(inputStream.available());
		
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filename, inputStream, objectMetadata);
		s3Client.putObject(putObjectRequest);
		
		URL objectUrl = s3Client.getUrl(bucketName, filename); // This will construct the URL
		return objectUrl.toExternalForm(); // This will return the URL as a String
	}
	
	
}
