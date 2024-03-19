package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CommonFacilityDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.repository.CommonFacilityRepository;
import com.rently.rentlyAPI.services.CommonFacilityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public CommonFacilityDto findCommonFacilityById(Integer commonFacilityId) {
        CommonFacility commonFacility = commonFacilityRepository.findById(commonFacilityId)
                .orElseThrow(() -> new IllegalArgumentException("CommonFacility with ID " + commonFacilityId + " not found"));
        return CommonFacilityDto.fromEntity(commonFacility);
    }

    @Override
    public List<CommonFacilityDto> getAllCommonFacilityByBuildingId(Integer buildingId) {
        List<CommonFacility> facilities = commonFacilityRepository.findAllByBuildingId(buildingId);
        return facilities.stream()
                .map(CommonFacilityDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommonFacilityDto> getAllCommonFacilities() {
        List<CommonFacility> facilities = commonFacilityRepository.findAll();
        return facilities.stream()
                .map(CommonFacilityDto::fromEntity)
                .collect(Collectors.toList());
    }
}
