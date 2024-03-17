//package com.rently.rentlyAPI.controller;
//
//import com.rently.rentlyAPI.dto.KeyDto;
//import com.rently.rentlyAPI.dto.UpdateProfileRequestDto;
//import com.rently.rentlyAPI.dto.UserProfileDto;
//import com.rently.rentlyAPI.auth.dto.ChangePasswordRequestDto;
//import com.rently.rentlyAPI.services.impl.S3ServiceImpl;
//import com.rently.rentlyAPI.services.impl.UserServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.security.Principal;
//
//@RestController
//@RequestMapping("/api/v1/users")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserServiceImpl userService;
//    private final S3ServiceImpl s3ServiceImpl;
//
//    //sample url : http://localhost:8080/api/v1/users/activateKeyToChangeRole?key=abc
//    @PostMapping("/activateKeyToChangeRole")
//    public ResponseEntity<KeyDto> activateKeyToChangeRole(@RequestParam String key) {
//        userService.activateKeyToChangeRole(key);
//        return ResponseEntity.ok().build();
//    }
//
//    @PatchMapping("/change-password")
//    public ResponseEntity<?> changePassword(
//          @RequestBody ChangePasswordRequestDto request,
//          Principal connectedUser
//    ) {
//        userService.changePassword(request, connectedUser);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/image-upload/{user_id}")
//    public ResponseEntity<?> uploadProfilePicture(
//        @RequestParam("image") MultipartFile multipartFile,
//        @PathVariable("user_id") Integer user_id
//    ) {
//        s3ServiceImpl.uploadImage(multipartFile, user_id);
//        return ResponseEntity.ok().body("Image uploaded successfully.");
//    }
//
//    @PatchMapping("/update-profile/{user_id}")
//    public ResponseEntity<?> updateProfile(
//        @RequestBody UpdateProfileRequestDto request,
//        @PathVariable("user_id") Integer user_id
//    ) {
//        userService.updateProfile(request, user_id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/view-profile/{user_id}")
//    public ResponseEntity<UserProfileDto> viewProfile(@PathVariable("user_id") Integer user_id) {
//        return ResponseEntity.ok(userService.viewProfile(user_id));
//    }
//}
