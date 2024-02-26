package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.KeyDto;
import com.rently.rentlyAPI.entity.Key;
import com.rently.rentlyAPI.repository.KeyRepository;
import com.rently.rentlyAPI.services.KeyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KeyServiceImpl implements KeyService {

    private final KeyRepository keyRepository;

    public Key save (Key key) {
        return keyRepository.save(key);
    }

    public Key findByKey(String key) {
        return keyRepository.findByKey(key);
    }




}
