package com.rently.rentlyAPI.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.rently.rentlyAPI.dto.UpdateProfileRequestDto;
import com.rently.rentlyAPI.dto.UserProfileDto;
import com.rently.rentlyAPI.dto.auth.ChangePasswordRequestDto;
import com.rently.rentlyAPI.entity.S3File;
import com.rently.rentlyAPI.entity.FileType;
import com.rently.rentlyAPI.services.impl.S3ServiceImpl;
import com.rently.rentlyAPI.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.Interceptor;
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
    
    @PostMapping("/image-upload/{user_id}")
    public ResponseEntity<?> uploadProfilePicture(
        @RequestParam("image") MultipartFile multipartFile,
        @PathVariable("user_id") Integer user_id
    ) {
        s3ServiceImpl.uploadImage(multipartFile, user_id);
        return ResponseEntity.ok().body("Image uploaded successfully.");
    }
    
    @PatchMapping("/update-profile/{user_id}")
    public ResponseEntity<?> updateProfile(
        @RequestBody UpdateProfileRequestDto request,
        @PathVariable("user_id") Integer user_id
    ) {
        userService.updateProfile(request, user_id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/view-profile/{user_id}")
    public ResponseEntity<UserProfileDto> viewProfile(@PathVariable("user_id") Integer user_id) {
        return ResponseEntity.ok(userService.viewProfile(user_id));
    }
}
