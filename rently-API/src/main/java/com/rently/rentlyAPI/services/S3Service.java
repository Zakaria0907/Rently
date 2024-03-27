package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CondoFileDto;
import org.hibernate.Interceptor;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface S3Service {
	//TODO: throw appropriate exception
	public void uploadImage(MultipartFile imageFile, String token) throws Exception;
	
	public void uploadCondoFile(MultipartFile multipartFile, String description, Integer condoId) throws Exception;
}
