import { createContext, useState, ReactNode } from "react";

interface AuthContextProps {
    auth: any;
    setAuth: (auth: any) => void;
    persist: boolean;
    setPersist: (persist: boolean) => void;
}

const AuthContext = createContext<AuthContextProps>({
    auth: {},
    setAuth: () => {},
    persist: false,
    setPersist: () => {},
});

interface AuthProviderProps {
    children: ReactNode;
}

export const AuthProvider = ({ children }: AuthProviderProps) => {
    const [auth, setAuth] = useState({});
    const [persist, setPersist] = useState(
        JSON.parse(localStorage.getItem("persist") || false as any)
    );

    return (
        <AuthContext.Provider value={{ auth, setAuth, persist, setPersist }}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthContext;