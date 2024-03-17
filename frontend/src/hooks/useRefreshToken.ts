import { AxiosResponse } from 'axios';
import ApiManager from '../api/ApiManager';
import useAuth from './useAuth';
import { User } from '../types/types';

// interface Auth {
//     access_token: string;
//     // Add other properties if needed
// }

const useRefreshToken = (): (() => Promise<string>) => {
    const { setAuth } = useAuth();

    const refresh = async (): Promise<string> => {
        const response: AxiosResponse<{ user: any; access_token: string, companyname?: string }> = await ApiManager.refresh({});
        setAuth(() => {
            console.log("response.data in useRefreshToken", response?.data);

            const user: User = {
                id: response?.data?.user?.id,
                firstname: response?.data?.user?.firstname,
                lastname: response?.data?.user?.lastname,
                phoneNumber: response?.data?.user?.phoneNumber,
                bio: response?.data?.user?.bio,
                email: response?.data?.user?.email,
                roles: response?.data?.user?.role,
            }

            return user;
        });
        return response.data.access_token;
    };

    return refresh;
};

export default useRefreshToken;