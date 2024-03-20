package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.services.OccupantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occupant")
@RequiredArgsConstructor
public class OccupantController {
    private final OccupantService occupantService;

    @PostMapping(path = "/create/facility-reservation")
    public ResponseEntity<CommonFacilityReservationDto> createFacilityReservation(@RequestHeader("Authorization") String token, @RequestBody CommonFacilityReservationDto commonFacilityReservationDto) {
        return ResponseEntity.ok(occupantService.createCommonFacilityReservation(token, commonFacilityReservationDto));
    }

    @DeleteMapping(path = "/delete/facility-reservation/{id}")
    public ResponseEntity<String> deleteFacilityReservation(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        occupantService.deleteCommonFacilityReservation(token, id);
        return ResponseEntity.ok("Reservation deleted successfully");
    }

    @GetMapping(path = "/facility-reservation/{id}")
    public ResponseEntity<CommonFacilityReservationDto> getFacilityReservation(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        return ResponseEntity.ok(occupantService.getCommonFacilityReservation(token, id));
    }

    @GetMapping(path = "/facility-reservation")
    public ResponseEntity<List<CommonFacilityReservationDto>> getAllFacilityReservations(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(occupantService.getAllCommonFacilityReservations(token));
    }

}

