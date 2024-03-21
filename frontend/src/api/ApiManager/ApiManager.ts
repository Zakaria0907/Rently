import ApiMethods from "./ApiMethods";
import ENDPOINTS from "./EndPoints"

const BASE_URL = 'http://localhost:8080/api';

class ApiManager {
    static helloWorld = () => {
        const url = BASE_URL + ENDPOINTS.HELLO_WORLD();
        return ApiMethods.get(url);
    }

    static register = (data: any) => {
        const url = BASE_URL + ENDPOINTS.REGISTER();
        return ApiMethods.post(url, data);
    }

    static login = (data: any) => {
        const url = BASE_URL + ENDPOINTS.LOGIN();
        return ApiMethods.post(url, data, true);
    }

    static refresh = (data: any) => {
        const url = BASE_URL + ENDPOINTS.REFRESH();
        return ApiMethods.post(url, data, true);
    }

    static logout = () => {
        const url = BASE_URL + ENDPOINTS.LOGOUT();
        return ApiMethods.post(url, {}, true);
    }

}

export default ApiManager;