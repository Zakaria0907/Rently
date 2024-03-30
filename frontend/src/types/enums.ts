export enum Roles {
    ADMIN = "SYSTEM_ADMIN",
    USER = "PUBLIC_USER",
    RENTER = "RENTER",
    OWNER = "OWNER",
    COMPANY= "COMPANY_ADMIN",
    EMPLOYEE = "EMPLOYEE",
}

export enum RoleText {
    ADMIN = "Administator",
    USER = "Public User",
    RENTER = "Renter",
    OWNER = "Owner",
    COMPANY = "Management Company",
}

export enum UnitStatus {
    AVAILABLE_FOR_SALE = "AVAILABLE_FOR_SALE",
    AVAILABLE_FOR_RENT = "AVAILABLE_FOR_RENT",
    RENTED = "RENTED",
}

export enum UnitType {
    APARTMENT = "APARTMENT",
    PENTHOUSE = "PENTHOUSE",
    FACILITIES = "COMMON_FACILITIES",
    OTHER = "OTHER",
}

export enum EmployeeType {
    ELECTRICITY = "ELECTRICITY",
    PLUMBING = "PLUMBING",
    CLEANING = "CLEANING",
    SECURITY = "SECURITY",
    GENERAL = "GENERAL",
}

export enum AssignmentStatus {
    NOT_ASSIGNED = "NOT_ASSIGNED",
    ASSIGNED = "ASSIGNED",
    IN_PROGRESS = "IN_PROGRESS",
    COMPLETED = "COMPLETED",
    CANCELLED = "CANCELLED",
}