package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.services.OccupantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/occupant")
@RequiredArgsConstructor
public class OccupantController {
    private final OccupantService occupantService;

    @PostMapping(path = "/create/facility-reservation")
    public ResponseEntity<CommonFacilityReservationDto> createFacilityReservation(@RequestHeader("Authorization") String token, @RequestBody CommonFacilityReservationDto commonFacilityReservationDto) {
        return ResponseEntity.ok(occupantService.createCommonFacilityReservation(token, commonFacilityReservationDto));
    }

}

