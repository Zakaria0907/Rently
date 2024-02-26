import ApiManager from "../api/ApiManager";
import useAuth from "./useAuth";
import { useNavigate } from "react-router-dom";

const useLogout = () => {
    const { setAuth, setPersist } = useAuth();
    const navigate = useNavigate();

    const logout = async () => {
        setAuth({});
        setPersist(false);
        console.log("logging out...");
        try {
            await ApiManager.logout();
            navigate("/");
        } catch (err) {
            console.error(err);
        }
    }

    return logout;
}

export default useLogout