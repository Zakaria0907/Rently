import { useLocation, Navigate, Outlet } from "react-router-dom";
import useAuth from "../hooks/useAuth";
import { useEffect } from "react";

interface RequireAuthProps {
    allowedRoles: string[];
}

const RequireAuth: React.FC<RequireAuthProps> = ({ allowedRoles }) => {
    const { auth } = useAuth();
    const location = useLocation();
    
    useEffect(() => {
        console.log("RequireAuth: auth: ", auth);
        console.log("RequireAuth: allowedRoles: ", allowedRoles);
        console.log("allowedRoles?.includes(auth?.roles)", allowedRoles?.includes(auth?.roles));
    }, []);

    return (
        // auth?.roles?.find((role: string) => allowedRoles?.includes(role))
        allowedRoles?.includes(auth?.roles)
            ? <Outlet />
            : auth?.access_token
                ? <Navigate to="/unauthorized" state={{ from: location }} replace />
                : <Navigate to="/login" state={{ from: location }} replace />
    );
}

export default RequireAuth;