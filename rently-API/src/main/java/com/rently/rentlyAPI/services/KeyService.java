package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.Key;

public interface KeyService {

    Key save (Key key);

    Key findByKey(String key);
}
