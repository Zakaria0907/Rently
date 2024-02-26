import axios from 'axios';

const getHeaders = () => {
  return {
    'Content-Type': 'application/json',
  };
};

class ApiMethods {
    static apiRequest(method: string, url: any, body = {}, withCredentials = false) {
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

        if (withCredentials) {
            options.withCredentials = true;
        }

        return axios(options)
            .then(response => response)
            .catch(error => Promise.reject(error));
    }

    static get(url: any, withCredentials = false) {
        return this.apiRequest('GET', url, {}, withCredentials);
    }

    static post(url: any, data: {} | undefined, withCredentials = false) {
        return this.apiRequest('POST', url, data, withCredentials);
    }

    static put(url: any, data: {} | undefined, withCredentials = false) {
        return this.apiRequest('PUT', url, data, withCredentials);
    }

    static delete(url: any, withCredentials = false) {
        return this.apiRequest('DELETE', url, {}, withCredentials);
    }
}

export default ApiMethods;