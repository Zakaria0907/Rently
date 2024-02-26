package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.dto.KeyDto;

public interface CompanyService {

   CondoDto createCondoByCompanyId(Integer companyId, CondoDto condoDto);

   KeyDto createActivationKeyToBecomeRenter(String userEmail, Integer companyId);
   KeyDto createActivationKeyToBecomeOwner(String userEmail, Integer companyId);
}
