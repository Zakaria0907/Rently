package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.services.CommonFacilityReservationService;

public class CommonFacilityReservationImpl implements CommonFacilityReservationService{
    @Override
    public PublicUserDto createReservation(CommonFacilityReservationDto commonFacilityReservationDto) {
        return null;
    }

    @Override
    public PublicUserDto updateReservation(CommonFacilityReservationDto commonFacilityReservationDto, Integer occupantID, Integer commonFacilityID) {
        return null;
    }

    @Override
    public PublicUserDto getreservation(Integer occupantID, Integer commonFacilityID) {
        return null;
    }

    @Override
    public String deletePublicUserById(Integer occupantID, Integer commonFacilityID) {
        return null;
    }
}
