package com.rently.rentlyAPI.entity.enums;

public enum CondoStatus {
    AVAILABLE, //Means that the condo doesn't have an owner/renter yet but is created by the company
    OWNED, //Means that the condo is owned
    RENTED //Means that the condo is rented
}
