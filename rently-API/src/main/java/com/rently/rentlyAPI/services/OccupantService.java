package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.entity.user.Occupant;

public interface OccupantService {
    CommonFacilityReservationDto createCommonFacilityReservation(String token, CommonFacilityReservationDto commonFacilityReservationDto);

    Occupant findOccupantEntityById(Integer occupantId);

    Occupant findOccupantEntityByEmail(String email);
}
