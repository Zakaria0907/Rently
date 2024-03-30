import { UnitStatus, UnitType, EmployeeType, AssignmentStatus } from "./enums";

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
    employee_type?: EmployeeType;
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
    unit_number: number;
    condoType: UnitType;
    registration_key: string | null;
    status: UnitStatus;
    userId?: number;
    building_id?: number;
    parking_id: number | null;
    locker_id: number | null;
}



export interface Assignment {
    id: number;
    company_id: number;
    employee_id: number;
    owner_request_id: number;
    work_type: string;
    status: AssignmentStatus;
    assignment_updates: AssignmentUpdate[];
}

export interface AssignmentUpdate {
    id: number;
    change_date: string;
    status: string;
    comment: string;
}
