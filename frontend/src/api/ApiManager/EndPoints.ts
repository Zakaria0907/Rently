const ENDPOINTS = {

    HELLO_WORLD: () => '/hello',
    REGISTER: () => '/auth/register',
    LOGIN: () => '/auth/authenticate',
    REFRESH: () => '/auth/refresh-token',
    LOGOUT: () => '/auth/logout',
    
}

export default ENDPOINTS;