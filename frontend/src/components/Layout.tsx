import { Outlet } from "react-router-dom"
import Navbar from "./Navbar"
import Footer from "./Footer"
import { useCart } from "../context/CartContext";
import SideBar from "./Sidebar/SideBar";

const Layout = () => {
    const { isCartVisible } = useCart(); // Use the useCart hook to get the cart visibility state

    return (
        <>
            <div className="flex flex-col min-h-screen">
                <Navbar />
                <div className="flex flex-1">
                    <div className={`flex-1 ${isCartVisible ? 'lg:w-11/12' : 'w-full'}`}>
                        <Outlet/>
                    </div>
                    <SideBar/>
                </div>
                <Footer />
            </div>
        </>
    )
}

export default Layout
