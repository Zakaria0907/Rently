import axios, { AxiosResponse } from 'axios';
import useAuth from './useAuth';

interface Auth {
    accessToken: string;
    // Add other properties if needed
}

const useRefreshToken = (): (() => Promise<string>) => {
    const { setAuth } = useAuth();

    const refresh = async (): Promise<string> => {
        const response: AxiosResponse<{ roles: any; accessToken: string }> = await axios.get('/refresh', {withCredentials: true });
        setAuth((prev: Auth) => {
            console.log(JSON.stringify(prev));
            console.log(response.data.accessToken);
            return {
                ...prev,
                roles: response.data.roles,
                accessToken: response.data.accessToken
            }
        });
        return response.data.accessToken;
    };

    return refresh;
};

export default useRefreshToken;