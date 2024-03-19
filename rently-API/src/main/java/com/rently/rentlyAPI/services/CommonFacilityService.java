package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CommonFacilityDto;

public interface CommonFacilityService {
    CommonFacilityDto createCommonFacilityAndLinkToBuilding(CommonFacilityDto commonFacilityDto);
}
