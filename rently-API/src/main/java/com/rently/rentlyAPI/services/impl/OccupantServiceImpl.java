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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OccupantServiceImpl implements OccupantService {
    private final BuildingService buildingService;
    private final RenterService renterService;
    private final OwnerService ownerService;

    @Override
    public CommonFacilityReservationDto createCommonFacilityReservation(CommonFacilityReservationDto commonFacilityReservationDto) {
        Occupant occupant = findOccupantEntityById(commonFacilityReservationDto.getOccupantId());
        return buildingService.createCommonFacilityReservation(occupant, commonFacilityReservationDto);
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
}
