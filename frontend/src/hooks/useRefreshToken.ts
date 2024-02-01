import axios, { AxiosResponse } from 'axios';
import { useState } from 'react';
import useAuth from './useAuth';

interface Auth {
    accessToken: string;
    // Add other properties if needed
}

const useRefreshToken = (): (() => Promise<string>) => {
    const { setAuth } = useAuth();

    const refresh = async (): Promise<string> => {
        const response: AxiosResponse<{ accessToken: string }> = await axios.get('/refresh', {
            withCredentials: true
        });
        setAuth((prev: Auth) => {
            console.log(JSON.stringify(prev));
            console.log(response.data.accessToken);
            return { ...prev, accessToken: response.data.accessToken };
        });
        return response.data.accessToken;
    };

    return refresh;
};

export default useRefreshToken;