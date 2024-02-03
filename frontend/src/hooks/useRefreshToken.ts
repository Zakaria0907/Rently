import axios, { AxiosResponse } from 'axios';
import useAuth from './useAuth';

// interface Auth {
//     access_token: string;
//     // Add other properties if needed
// }

const useRefreshToken = (): (() => Promise<string>) => {
    const { setAuth } = useAuth();

    const refresh = async (): Promise<string> => {
        const response: AxiosResponse<{ user: any; access_token: string }> = await axios.get('/auth/refresh-token', {withCredentials: true });
        setAuth((prev: any) => {
            console.log("JSON.stringify.prev in useRefreshToken", JSON.stringify(prev));
            console.log("response.data.access_token in useRefreshToken", response.data.access_token);
            return {
                ...prev,
                roles: response.data.user.role,
                access_token: response.data.access_token
            }
        });
        return response.data.access_token;
    };

    return refresh;
};

export default useRefreshToken;