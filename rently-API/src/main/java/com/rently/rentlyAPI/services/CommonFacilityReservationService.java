package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.dto.PublicUserDto;

public interface CommonFacilityReservationService {

    PublicUserDto createReservation(CommonFacilityReservationDto commonFacilityReservationDto);

    PublicUserDto updateReservation(CommonFacilityReservationDto commonFacilityReservationDto, Integer occupantID, Integer commonFacilityID);

    PublicUserDto getreservation(Integer occupantID, Integer commonFacilityID);

//    PublicUserDto getReservationByEmail(String publicUserEmail);

    String deletePublicUserById(Integer occupantID, Integer commonFacilityID);


}
