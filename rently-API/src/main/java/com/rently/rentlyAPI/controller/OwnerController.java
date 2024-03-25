package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.OwnerRequestDto;
import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.services.OccupantService;
import com.rently.rentlyAPI.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;
    private final OccupantService occupantService;

    @PostMapping(path = "/create/request")
    public ResponseEntity<OwnerRequestDto> createOwnerRequest(@RequestHeader("Authorization") String token, @RequestBody OwnerRequestDto ownerRequestDto) {
        Owner owner = (Owner) occupantService.findOccupantEntityByToken(token);
        return ResponseEntity.ok(ownerService.createOwnerRequest(owner, ownerRequestDto));
    }


}
