package com.rently.rentlyAPI.controller;

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
        try {
            s3ServiceImpl.uploadImage(multipartFile, connectedUser);
            return ResponseEntity.ok().body("Image uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }
}
