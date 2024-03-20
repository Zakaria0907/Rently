package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.auth.dto.ChangePasswordRequestDto;
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
	
	@PostMapping(path = "/activate-key/key={registrationKey}")
	public ResponseEntity<String> activateKeyToChangeRole(@RequestHeader("Authorization") String token, @PathVariable(name = "registrationKey") String registrationKey) {
		return ResponseEntity.ok(userService.userKeyActivation(token, registrationKey));
	}
}
