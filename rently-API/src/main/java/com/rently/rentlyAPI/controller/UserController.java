package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.KeyDto;
import com.rently.rentlyAPI.dto.auth.ChangePasswordRequestDto;
import com.rently.rentlyAPI.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;

    //sample url : http://localhost:8080/api/v1/users/activateKeyToChangeRole?key=abc
    @PostMapping("/activateKeyToChangeRole")
    public ResponseEntity<KeyDto> activateKeyToChangeRole(@RequestParam String key) {
        service.activateKeyToChangeRole(key);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequestDto request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
