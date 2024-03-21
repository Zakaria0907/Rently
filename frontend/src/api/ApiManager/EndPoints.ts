const ENDPOINTS = {

    HELLO_WORLD: () => '/hello',
    REGISTER: () => '/auth/register',
    LOGIN: () => '/authentication/authenticate',
    REFRESH: () => '/authentication/refresh-token',
    LOGOUT: () => '/auth/logout',
    VIEW_PROFILE: () => '/users/view-profile/1',
    
}

export default ENDPOINTS;