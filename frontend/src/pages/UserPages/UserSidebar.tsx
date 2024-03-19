import { NavLink, useLocation } from 'react-router-dom';
import { FaRegUser } from "react-icons/fa";
import { RxDashboard } from "react-icons/rx";
import { IoSettingsOutline } from "react-icons/io5";


const UserSidebar = () => {
    const location = useLocation();
    const { pathname } = location;

    return (
        <ul className="mb-6 flex flex-col gap-1.5">

            {/* <!-- Menu Item Dashboard --> */}
            <li>
                <NavLink
                    to="/user-dashboard"
                    className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${pathname.includes('calendar') &&
                        'bg-graydark dark:bg-meta-4'
                        }`}
                >
                    <RxDashboard />
                    Dashboard
                </NavLink>
            </li>
            {/* <!-- Menu Item Dashboard --> */}


            {/* <!-- Menu Item Profile --> */}
            <li>
                <NavLink
                    to="/user-profile"
                    className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${pathname.includes('profile') && 'bg-graydark dark:bg-meta-4'
                        }`}
                >
                    <FaRegUser />
                    Profile
                </NavLink>
            </li>
            {/* <!-- Menu Item Profile --> */}


            {/* <!-- Menu Item Settings --> */}
            <li>
                <NavLink
                    to="/user-settings"
                    className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${pathname.includes('settings') &&
                        'bg-graydark dark:bg-meta-4'
                        }`}
                >
                    <IoSettingsOutline />
                    Settings
                </NavLink>
            </li>
            {/* <!-- Menu Item Settings --> */}
        </ul>
    );
};

export default UserSidebar;
