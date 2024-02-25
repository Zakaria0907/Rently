package com.rently.rentlyAPI.services;

import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface S3Service {
	//TODO: throw appropriate exception
	public void uploadImage(MultipartFile imageFile, Principal username) throws Exception;
}
