import { UnitStatus, UnitType } from "./enums";

export interface exampleInterface {
    string: string;
    number: number;
    boolean: boolean;
    array: string[];
}

export interface User {
    id: number;
    firstname: string;
    lastname: string;
    phoneNumber: string;
    bio: string;
    email: string;
    roles: string;
    company_id?: number;
}

export interface Building {
    id?: number;
    name: string;
    numberOfFloors: number;
    address: string;
    description: string;
    companyId?: number;
    userId?: number;
}

export interface Unit {
    id?: number;
    name: string;
    address: string;
    condoNumber: string;
    condoType: UnitType;
    description: string;
    status: UnitStatus;
    userId?: number;
    buildingId?: number;
}