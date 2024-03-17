//package com.rently.rentlyAPI.services.impl;
//
//import com.rently.rentlyAPI.dto.CondoDto;
//import com.rently.rentlyAPI.entity.Building;
//import com.rently.rentlyAPI.entity.Condo;
//import com.rently.rentlyAPI.repository.CondoRepository;
//import com.rently.rentlyAPI.services.CondoService;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class CondoServiceImpl implements CondoService {
//
//    private final CondoRepository condoRepository;
//
//    @Override
//    public Condo save(Condo condo) {
//        return condoRepository.save(condo);
//    }
//
//    @Override
//    public Condo update(CondoDto condoDto, Condo condo) {
//        if (condoDto.getName() != null) condo.setName(condoDto.getName());
//        if (condoDto.getAddress() != null) condo.setAddress(condoDto.getAddress());
//        if (condoDto.getCondoNumber() != null) condo.setCondoNumber(condoDto.getCondoNumber());
//        if (condoDto.getCondoType() != null) condo.setCondoType(condoDto.getCondoType());
//        if (condoDto.getDescription() != null) condo.setDescription(condoDto.getDescription());
//        if (condoDto.getStatus() != null) condo.setStatus(condoDto.getStatus());
//
//        return condoRepository.save(condo);
//    }
//
//    @Override
//    public void delete(Condo condo) {
//        condoRepository.delete(condo);
//    }
//
//    @Override
//    public boolean exists(Condo condo) {
//        return condoRepository.existsById(condo.getId());
//    }
//
//    public boolean exists(Integer buildingId) {
//        return buildingId != null && condoRepository.existsById(buildingId);
//    }
//
//    @Override
//    public Condo findById(Integer id) {
//        return condoRepository.findById(id)
//            .orElseThrow(() -> new EntityNotFoundException("Condo with id " + id + " not found"));
//    }
//
//    @Override
//    public Integer countCondosByBuildingId(Integer buildingId) {
//        return condoRepository.countCondosById(buildingId);
//    }
//
//    @Override
//    public List<Condo> findAllCondosByBuildingId(Integer buildingId) {
//        return condoRepository.findAllCondosById(buildingId);
//    }
//}
