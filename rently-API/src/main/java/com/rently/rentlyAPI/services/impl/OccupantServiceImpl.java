package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.entity.user.Occupant;
import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.entity.user.Renter;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.services.BuildingService;
import com.rently.rentlyAPI.services.OccupantService;
import com.rently.rentlyAPI.services.OwnerService;
import com.rently.rentlyAPI.services.RenterService;
import com.rently.rentlyAPI.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OccupantServiceImpl implements OccupantService {
    private final BuildingService buildingService;
    private final RenterService renterService;
    private final OwnerService ownerService;
    private final JwtUtils jwtUtils;

    @Override
    public CommonFacilityReservationDto createCommonFacilityReservation(String token, CommonFacilityReservationDto commonFacilityReservationDto) {
        Occupant occupantToLink = findOccupantEntityByToken(token);
        return buildingService.createCommonFacilityReservation(occupantToLink, commonFacilityReservationDto);
    }

    private Occupant findOccupantEntityByToken(String token) {
        String tokenWithoutBearer = token.substring(7);
        String email = jwtUtils.extractUsername(tokenWithoutBearer);
        return findOccupantEntityByEmail(email);

    }

    @Override
    public Occupant findOccupantEntityById(Integer occupantId) {
        Optional<Owner> owner = ownerService.findOwnerEntityById(occupantId);
        Optional<Renter> renter = renterService.findRenterEntityById(occupantId);
        if (owner.isPresent()) {
            return owner.get();
        }
        if (renter.isPresent()) {
            return renter.get();
        }
        throw new AuthenticationException("Occupant with ID " + occupantId + " not found");
    }

    @Override
    public Occupant findOccupantEntityByEmail(String email) {
        Optional<Owner> owner = ownerService.findByEmail(email);
        Optional<Renter> renter = renterService.findByEmail(email);
        if (owner.isPresent()) {
            return owner.get();
        }
        if (renter.isPresent()) {
            return renter.get();
        }
        throw new AuthenticationException("Occupant with ID " + email + " not found");
    }

    @Override
    public void deleteCommonFacilityReservation(String token, Integer id) {
        Integer occupantId = findOccupantEntityByToken(token).getId();
        buildingService.deleteCommonFacilityReservation(occupantId, id);
    }

    @Override
    public CommonFacilityReservationDto getCommonFacilityReservation(String token, Integer id) {
        Integer occupantId = findOccupantEntityByToken(token).getId();
        return buildingService.getCommonFacilityReservation(occupantId, id);
    }

    @Override
    public List<CommonFacilityReservationDto> getAllCommonFacilityReservations(String token) {
        Integer occupantId = findOccupantEntityByToken(token).getId();
        return buildingService.getAllCommonFacilityReservations(occupantId);
    }

}
