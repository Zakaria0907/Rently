package com.rently.rentlyAPI.exceptions;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

import java.io.IOException;

public class FileUploadException extends RuntimeException {
	public FileUploadException(String message) {
		super(message);
	}
	
	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public static FileUploadException wrap(Exception e) {
		if (e instanceof IOException || e instanceof SdkClientException) {
			return new FileUploadException(e.getMessage(), e);
		} else {
			throw new IllegalArgumentException("Exception not related to file uploading", e);
		}
	}
}
