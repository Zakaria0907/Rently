import useLogout from "../hooks/useLogout";
import useRefreshToken from "../hooks/useRefreshToken";

const Welcome: React.FC = () => {
    const logout = useLogout();

    const signOut = async () => {
        await logout();
    }

    const refresh = useRefreshToken();

    return (
        <div className="flex justify-center items-center h-screen bg-gray-100">
            <h1 className="text-4xl font-bold text-gray-800">Welcome!</h1>
            <p className="text-lg text-gray-600 mt-4">You are logged in.</p>
            <div className="mt-4">
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" onClick={signOut}>Sign Out</button>
            </div>
            <div className="mt-4">
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" onClick={refresh}>Refresh Token</button>
            </div>
        </div>
    );
};

export default Welcome;
