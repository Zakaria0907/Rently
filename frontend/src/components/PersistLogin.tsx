import { Outlet } from "react-router-dom";
import { useState, useEffect } from "react";
import useRefreshToken from '../hooks/useRefreshToken';
import useAuth from '../hooks/useAuth';
import { Spinner } from "@chakra-ui/react";

const PersistLogin = () => {
    const [isLoading, setIsLoading] = useState(true);
    const refresh = useRefreshToken();
    const { auth, persist } = useAuth();

    useEffect(() => {
        let isMounted = true;

        const verifyRefreshToken = async () => {
            try {
                console.log("verifyRefreshToken: refreshing token...")
                await refresh();
            }
            catch (err) {
                console.error(err);
            }
            finally {
                console.log("verifyRefreshToken: done refreshing token")
                isMounted && setIsLoading(false);
            }
        }

        // persists avoids unwanted call to verifyRefreshToken
        !auth?.access_token && persist ? verifyRefreshToken() : setIsLoading(false);

        //check this
        return () => {
            isMounted = false;
        };
    }, [])

    useEffect(() => {
        console.log(`isLoading: ${isLoading}`)
        console.log(`auth?.access_token: ${JSON.stringify(auth?.access_token)}`)
    }, [isLoading])

    return (
        <>
            {!persist
                ? <Outlet />
                : isLoading
                    ? <div className="flex justify-center items-center h-screen">
                        <Spinner
                            thickness='4px'
                            speed='0.65s'
                            emptyColor='gray.200'
                            color='blue.500'
                            size='xl'
                        />
                    </div>

                    : <Outlet />
            }
        </>
    )
}

export default PersistLogin