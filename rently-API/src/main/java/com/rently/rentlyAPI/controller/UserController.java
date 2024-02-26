package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.KeyDto;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.rently.rentlyAPI.dto.auth.ChangePasswordRequestDto;
import com.rently.rentlyAPI.entity.S3File;
import com.rently.rentlyAPI.entity.FileType;
import com.rently.rentlyAPI.services.impl.S3ServiceImpl;
import com.rently.rentlyAPI.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final S3ServiceImpl s3ServiceImpl;

    //sample url : http://localhost:8080/api/v1/users/activateKeyToChangeRole?key=abc
    @PostMapping("/activateKeyToChangeRole")
    public ResponseEntity<KeyDto> activateKeyToChangeRole(@RequestParam String key) {
        userService.activateKeyToChangeRole(key);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequestDto request,
          Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/image-upload")
    public ResponseEntity<?> uploadProfilePicture(
        @RequestParam("imageFile") MultipartFile multipartFile,
        Principal connectedUser
    ) {
        s3ServiceImpl.uploadImage(multipartFile, connectedUser);
        return ResponseEntity.ok().body("Image uploaded successfully.");
    }
}
