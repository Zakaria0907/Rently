package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CommonFacilityDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.repository.CommonFacilityRepository;
import com.rently.rentlyAPI.services.BuildingService;
import com.rently.rentlyAPI.services.CommonFacilityService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommonFacilityServiceImpl implements CommonFacilityService {
    private final CommonFacilityRepository commonFacilityRepository;
    private final BuildingService buildingService;

    @Override
    public CommonFacilityDto createCommonFacilityAndLinkToBuilding(CommonFacilityDto commonFacilityDto) {
        Optional<CommonFacility> existingCommonFacility = commonFacilityRepository.findByName(commonFacilityDto.getFacilityName());
        if (existingCommonFacility.isPresent()) {
            throw new EntityExistsException("Common Facility with name " + commonFacilityDto.getFacilityName() + " already exists");
        }

        Building buildingToLink = buildingService.findBuildingEntityById(commonFacilityDto.getBuildingId());
        CommonFacility commonFacility = CommonFacilityDto.toEntity(commonFacilityDto);
        commonFacility.setBuilding(buildingToLink);
        CommonFacility savedCommonFacility = commonFacilityRepository.save(commonFacility);
        return CommonFacilityDto.fromEntity(savedCommonFacility);
    }
}
