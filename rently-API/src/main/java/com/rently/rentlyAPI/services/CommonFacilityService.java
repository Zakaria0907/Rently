package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CommonFacilityDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.CommonFacility;

import java.util.List;
import java.util.Optional;

public interface CommonFacilityService {
    CommonFacilityDto createCommonFacilityAndLinkToBuilding(CommonFacilityDto commonFacilityDto, Building buildingToLink);

    Optional<CommonFacility> findCommonFacilityByName(Integer buildingId, String facilityName);

    CommonFacilityDto findCommonFacilityById(Integer commonFacilityId);

    List<CommonFacilityDto> getAllCommonFacilityByBuildingId(Integer buildingId);
}
