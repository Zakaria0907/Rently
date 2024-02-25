package com.rently.rentlyAPI.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.rently.rentlyAPI.dto.auth.ChangePasswordRequestDto;
import com.rently.rentlyAPI.entity.S3File;
import com.rently.rentlyAPI.entity.s3_enums.ImageType;
import com.rently.rentlyAPI.services.S3Service;
import com.rently.rentlyAPI.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;
    private final S3Service s3Service;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequestDto request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
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
