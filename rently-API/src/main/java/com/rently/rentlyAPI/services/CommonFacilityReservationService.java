package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.user.Occupant;

public interface CommonFacilityReservationService {

    CommonFacilityReservationDto createCommonFacilityReservation(Company company, CommonFacility commonFacility, Occupant occupant, CommonFacilityReservationDto commonFacilityReservationDto);

    CommonFacilityReservationDto updateReservation(CommonFacilityReservationDto commonFacilityReservationDto, Integer occupantID, Integer commonFacilityID);

    CommonFacilityReservationDto getReservation(Integer occupantID, Integer commonFacilityID);

//    PublicUserDto getReservationByEmail(String publicUserEmail);

    String deletePublicUserById(Integer occupantID, Integer commonFacilityID);


}
