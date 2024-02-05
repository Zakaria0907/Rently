import axios from 'axios';

const getHeaders = () => {
  return {
    'Content-Type': 'application/json',
  };
};

class ApiMethods {
    static apiRequest(method: string, url: any, body = {}, privateCall = false) {
        const options = {
            method: method,
            headers: getHeaders(),
            url: url,
            data: body,
            withCredentials: false
        };

        if (method !== 'GET' && method !== 'DELETE') {
            options.data = body;
        }

        if (privateCall) {
            options.withCredentials = true;
        }

        return axios(options)
            .then(response => response)
            .catch(error => Promise.reject(error));
    }

    static get(url: any, privateCall = false) {
        return this.apiRequest('GET', url, {}, privateCall);
    }

    static post(url: any, data: {} | undefined, privateCall = false) {
        return this.apiRequest('POST', url, data, privateCall);
    }

    static put(url: any, data: {} | undefined, privateCall = false) {
        return this.apiRequest('PUT', url, data, privateCall);
    }

    static delete(url: any, privateCall = false) {
        return this.apiRequest('DELETE', url, {}, privateCall);
    }
}

export default ApiMethods;