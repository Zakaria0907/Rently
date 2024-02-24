package com.rently.rentlyAPI.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.rently.rentlyAPI.auth.domain.dto.ChangePasswordRequestDto;
import com.rently.rentlyAPI.auth.service.UserService;
import com.rently.rentlyAPI.domain.entity.S3File;
import com.rently.rentlyAPI.domain.enums.ImageType;
import com.rently.rentlyAPI.service.S3Service;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    //TODO: fix update-profile

    private final UserService userService;
    private final S3Service s3Service;
    
    
    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequestDto request,
          Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/image-upload")
    public ResponseEntity<?> imageUpload(
        @RequestParam("description") String description,
        @RequestParam("imageFile") MultipartFile multipartFile
    ) {
        String fileName = multipartFile.getOriginalFilename();
        S3File s3File = new S3File(description, fileName, ImageType.JPEG);
        
        try {
            String storedUrl = s3Service.uploadToS3(multipartFile.getInputStream(), fileName);
            return ResponseEntity.ok("File uploaded successfully at: " + storedUrl);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("AWS Service Error");
        } catch (SdkClientException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("SDK Client Error");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("IO Error");
        }
    }
}
