package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.entity.CommonFacilityReservation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CommonFacilityReservationDtoTest {

    @Test
    public void testFromEntity() {
        CommonFacilityReservation commonFacilityReservation = new CommonFacilityReservation();
        commonFacilityReservation.setId(1);
        commonFacilityReservation.setDate("2024-03-21");

        CommonFacility commonFacility = new CommonFacility();
        commonFacility.setId(1);
        commonFacilityReservation.setCommonFacility(commonFacility);

        CommonFacilityReservationDto commonFacilityReservationDto = CommonFacilityReservationDto.fromEntity(commonFacilityReservation);

        assertEquals(commonFacilityReservation.getId(), commonFacilityReservationDto.getId());
        assertEquals(commonFacilityReservation.getCommonFacility().getId(), commonFacilityReservationDto.getCommonFacilityId());
        assertEquals(commonFacilityReservation.getDate(), commonFacilityReservationDto.getReservationDate());
    }

    @Test
    public void testToEntity() {
        CommonFacilityReservationDto commonFacilityReservationDto = CommonFacilityReservationDto.builder()
                .commonFacilityId(0)
                .reservationDate("2024-03-21")
                .build();

        CommonFacility commonFacility = mock(CommonFacility.class);


        CommonFacilityReservation commonFacilityReservation = CommonFacilityReservationDto.toEntity(commonFacilityReservationDto);
        commonFacilityReservation.setCommonFacility(commonFacility);

        assertEquals(commonFacilityReservationDto.getCommonFacilityId(), commonFacilityReservation.getCommonFacility().getId());
        assertEquals(commonFacilityReservationDto.getReservationDate(), commonFacilityReservation.getDate());
    }
}
