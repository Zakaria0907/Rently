package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.dto.HousingContractAndCondoDto;
import com.rently.rentlyAPI.dto.OwnerRequestDto;
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

    // facility reservations
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

    // owner requests
    @PostMapping(path = "/create/request")
    public ResponseEntity<OwnerRequestDto> createOwnerRequest(@RequestHeader("Authorization") String token, @RequestBody OwnerRequestDto ownerRequestDto) {
        return ResponseEntity.ok(occupantService.createOwnerRequest(token, ownerRequestDto));
    }

    @DeleteMapping(path = "/delete/request/id={id}")
    public ResponseEntity<String> deleteOwnerRequest(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        occupantService.deleteOwnerRequest(token, id);
        return ResponseEntity.ok("Owner request with ID " + id + " deleted successfully");
    }

    @GetMapping(path = "/request")
    public ResponseEntity<List<OwnerRequestDto>> getAllOwnerRequests(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(occupantService.getAllOwnerRequests(token));
    }

    @GetMapping(path = "/request/id={id}")
    public ResponseEntity<OwnerRequestDto> getOwnerRequestById(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        return ResponseEntity.ok(occupantService.getOwnerRequestById(token, id));
    }
    
    // get condo of an occupant just by its token
    @GetMapping(path = "/my-condos")
    public ResponseEntity<List<CondoDto>> getMyCondos(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(occupantService.getMyCondos(token));
    }
    
    // get condo by its id
    @GetMapping(path = "/my-condo/condoId={condoId}")
    public ResponseEntity<HousingContractAndCondoDto> getMyCondoInformationById(@RequestHeader("Authorization") String token, @PathVariable Integer condoId) {
        return ResponseEntity.ok(occupantService.getMyCondoInformationById(token, condoId));
    }
    

}

