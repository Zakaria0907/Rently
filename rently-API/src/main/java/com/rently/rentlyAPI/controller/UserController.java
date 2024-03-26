package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.auth.dto.ChangePasswordRequestDto;
import com.rently.rentlyAPI.services.S3Service;
import com.rently.rentlyAPI.services.UserService;

import com.rently.rentlyAPI.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final S3Service s3Service;
	
	@PostMapping(path = "/activate-key/key={registrationKey}")
	public ResponseEntity<String> activateKeyToChangeRole(@RequestHeader("Authorization") String token, @PathVariable(name = "registrationKey") String registrationKey) {
		return ResponseEntity.ok(userService.userKeyActivation(token, registrationKey));
	}

	@PostMapping("/profile-picture/update")
	public ResponseEntity<?> uploadProfilePicture(
			@RequestParam("image") MultipartFile multipartFile,
			@RequestHeader("Authorization") String token
	) throws Exception {
		s3Service.uploadImage(multipartFile, token.substring(7));
		return ResponseEntity.ok().body("Image uploaded successfully.");
	}
}
