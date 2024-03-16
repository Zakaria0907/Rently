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
}

export interface Building {
    id?: number;
    name: string;
    address: string;
    unitCount?: number;
    description: string;
    userId?: number;
}

export type Unit = {
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