package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.user.Occupant;

import java.util.List;

public interface CommonFacilityReservationService {

    CommonFacilityReservationDto createCommonFacilityReservation(Company company, CommonFacility commonFacility, Occupant occupant, CommonFacilityReservationDto commonFacilityReservationDto);


    void deleteCommonFacilityReservation(Integer occupantId, Integer id);

    CommonFacilityReservationDto findCommonFacilityReservationDtoById(Integer occupantId, Integer id);

    List<CommonFacilityReservationDto> getAllCommonFacilityReservations(Integer occupantId);
}
