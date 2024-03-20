package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;

public interface CommonFacilityReservationService {

    CommonFacilityReservationDto createReservation(CommonFacilityReservationDto commonFacilityReservationDto);

    CommonFacilityReservationDto updateReservation(CommonFacilityReservationDto commonFacilityReservationDto, Integer occupantID, Integer commonFacilityID);

    CommonFacilityReservationDto getReservation(Integer occupantID, Integer commonFacilityID);

//    PublicUserDto getReservationByEmail(String publicUserEmail);

    String deletePublicUserById(Integer occupantID, Integer commonFacilityID);


}
