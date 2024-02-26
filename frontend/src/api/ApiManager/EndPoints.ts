const ENDPOINTS = {

    HELLO_WORLD: () => '/hello',
    REGISTER: () => '/auth/register',
    LOGIN: () => '/auth/authenticate',
    REFRESH: () => '/auth/refresh-token',
    LOGOUT: () => '/auth/logout',
    VIEW_PROFILE: () => '/users/view-profile/1',
    
}

export default ENDPOINTS;