package com.rently.rentlyAPI.entity.enums;

public enum CondoStatus {
    AVAILABLE_FOR_SALE, //Means that the condo doesn't have an owner yet but is created by the company
    AVAILABLE_FOR_RENT, //Means that the condo is OWNED by an owner AND is available for rent
    RENTED //Means that the condo is rented by a renter
}
