package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.HousingContract;
import com.rently.rentlyAPI.entity.user.Occupant;
import com.rently.rentlyAPI.entity.user.Owner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

//@SpringBootTest
public class HousingContractDtoTest {

    @Test
    public void testFromEntity() {
        HousingContract housingContract = new HousingContract();
        housingContract.setId(1);
        housingContract.setMonthlyRent(1000);
        housingContract.setOccupantType("Tenant");

        Company company = new Company();
        company.setId(1);
        housingContract.setCompany(company);

        Occupant occupant = new Owner();
        occupant.setId(1);
        housingContract.setOccupant(occupant);

        Condo condo = new Condo();
        condo.setId(1);
        housingContract.setCondo(condo);

        HousingContractDto housingContractDto = HousingContractDto.fromEntity(housingContract);

        assertNotNull(housingContractDto);
        assertEquals(housingContract.getId(), housingContractDto.getId());
        assertEquals(housingContract.getMonthlyRent(), housingContractDto.getMonthlyRent());
        assertEquals(housingContract.getOccupantType(), housingContractDto.getOccupantType());
        assertEquals(housingContract.getCompany().getId(), housingContractDto.getCompanyId());
        assertEquals(housingContract.getOccupant().getId(), housingContractDto.getOccupantId());
        assertEquals(housingContract.getCondo().getId(), housingContractDto.getCondoId());
    }

    @Test
    public void testToEntity() {
        HousingContractDto housingContractDto = mock(HousingContractDto.class);
        housingContractDto.setId(1);
        housingContractDto.setMonthlyRent(1000);
        housingContractDto.setOccupantType("Tenant");
        housingContractDto.setCompanyId(1);
        housingContractDto.setOccupantId(1);
        housingContractDto.setCondoId(1);

        HousingContract housingContract = HousingContractDto.toEntity(housingContractDto);

        assertNotNull(housingContract);
        assertEquals(housingContractDto.getMonthlyRent(), housingContract.getMonthlyRent());
        assertEquals(housingContractDto.getOccupantType(), housingContract.getOccupantType());
    }
}
