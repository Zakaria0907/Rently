import { AxiosResponse } from 'axios';
import ApiManager from '../api/ApiManager';
import useAuth from './useAuth';

// interface Auth {
//     access_token: string;
//     // Add other properties if needed
// }

const useRefreshToken = (): (() => Promise<string>) => {
    const { setAuth } = useAuth();

    const refresh = async (): Promise<string> => {
        const response: AxiosResponse<{ user: any; access_token: string, companyname?: string }> = await ApiManager.refresh({});
        setAuth((prev: any) => {
            console.log("response.data in useRefreshToken", response?.data);
            // console.log("JSON.stringify.prev in useRefreshToken", JSON.stringify(prev));
            // console.log("response.data.access_token in useRefreshToken", response?.data?.access_token);
            const access_token = response?.data?.access_token;
            const roles = response?.data?.user?.role;
            const company = response?.data?.companyname;
            const firstname = response?.data?.user?.firstname;
            const lastname = response?.data?.user?.lastname;
            const email = response?.data?.user?.email;
            return {
                ...prev,
                roles,
                access_token,
                company,
                firstname,
                lastname,
                email,
            }
        });
        return response.data.access_token;
    };

    return refresh;
};

export default useRefreshToken;