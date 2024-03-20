package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.entity.CommonFacilityReservation;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.user.Occupant;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.CommonFacilityReservationRepository;
import com.rently.rentlyAPI.services.CommonFacilityReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommonFacilityReservationServiceImpl implements CommonFacilityReservationService {

    private final CommonFacilityReservationRepository commonFacilityReservationRepository;

    @Override
    public CommonFacilityReservationDto createCommonFacilityReservation(Company company, CommonFacility commonFacility, Occupant occupant, CommonFacilityReservationDto commonFacilityReservationDto) {
        //check if there is already a reservation at the given time slot for the given user
        Optional<CommonFacilityReservation> ownExistingReservation = commonFacilityReservationRepository.findByOccupantIdAndDate(occupant.getId(), commonFacilityReservationDto.getReservationDate());
        if (ownExistingReservation.isPresent()) {
            throw new OperationNonPermittedException("There is already a reservation at the given time slot for occupant with id " + occupant.getId());
        }

        Optional<CommonFacilityReservation> otherExistingReservation = commonFacilityReservationRepository.findByCommonFacilityIdAndDate(commonFacility.getId(), commonFacilityReservationDto.getReservationDate());
        if (otherExistingReservation.isPresent()) {
            throw new OperationNonPermittedException("There is already a reservation at the given time slot by another occupant.");
        }


        CommonFacilityReservation commonFacilityReservation = CommonFacilityReservationDto.toEntity(commonFacilityReservationDto);

        commonFacilityReservation.setCompany(company);
        commonFacilityReservation.setCommonFacility(commonFacility);
        commonFacilityReservation.setOccupant(occupant);

        CommonFacilityReservation savedReservation = commonFacilityReservationRepository.save(commonFacilityReservation);
        return CommonFacilityReservationDto.fromEntity(savedReservation);
    }

    @Override
    public void deleteCommonFacilityReservation(Integer occupantId, Integer id) {
        //checks that the user has the right to delete the reservation
        findCommonFacilityReservationDtoById(occupantId, id);
        commonFacilityReservationRepository.deleteById(id);
    }

    @Override
    public CommonFacilityReservationDto findCommonFacilityReservationDtoById(Integer occupantId, Integer id) {
        Optional<CommonFacilityReservation> reservation = commonFacilityReservationRepository.findById(id);
        if (reservation.isEmpty()) throw new AuthenticationException("Reservation with ID " + id + " not found");
        if (reservation.get().getOccupant().getId().equals(occupantId)) {
            return CommonFacilityReservationDto.fromEntity(reservation.get());
        }
        throw new AuthenticationException("You do not have access to other people's reservations");
    }

    @Override
    public List<CommonFacilityReservationDto> getAllCommonFacilityReservations(Integer occupantId) {
        return commonFacilityReservationRepository.findAllByOccupantId(occupantId).stream()
                .map(CommonFacilityReservationDto::fromEntity)
                .collect(Collectors.toList());
    }
}
