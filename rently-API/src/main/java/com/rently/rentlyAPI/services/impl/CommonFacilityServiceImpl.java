package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CommonFacilityDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.repository.CommonFacilityRepository;
import com.rently.rentlyAPI.services.CommonFacilityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommonFacilityServiceImpl implements CommonFacilityService {
    private final CommonFacilityRepository commonFacilityRepository;

    @Override
    public CommonFacilityDto createCommonFacilityAndLinkToBuilding(CommonFacilityDto commonFacilityDto, Building buildingToLink) {
        CommonFacility commonFacility = CommonFacilityDto.toEntity(commonFacilityDto);
        commonFacility.setBuilding(buildingToLink);
        CommonFacility savedCommonFacility = commonFacilityRepository.save(commonFacility);
        return CommonFacilityDto.fromEntity(savedCommonFacility);
    }

    @Override
    public Optional<CommonFacility> findCommonFacilityByName(Integer buildingId, String facilityName) {
        return commonFacilityRepository.findByNameAndBuildingId(buildingId, facilityName);
    }
}
