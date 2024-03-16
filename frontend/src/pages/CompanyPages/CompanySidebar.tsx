import { NavLink, useLocation } from 'react-router-dom';
import { FaRegBuilding, FaRegUser, FaRegChartBar } from "react-icons/fa";
import { RxDashboard } from "react-icons/rx";
import { IoCalendarOutline, IoSettingsOutline } from "react-icons/io5";


const CompanySidebar = () => {
    const location = useLocation();
    const { pathname } = location;

    return (
        <ul className="mb-6 flex flex-col gap-1.5">

            {/* <!-- Menu Item Dashboard --> */}
            <li>
                <NavLink
                    to="/company-dashboard"
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
                    to="/company-profile"
                    className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${pathname.includes('profile') && 'bg-graydark dark:bg-meta-4'
                        }`}
                >
                    <FaRegUser />
                    Profile
                </NavLink>
            </li>
            {/* <!-- Menu Item Profile --> */}

            {/* <!-- Menu Item Buildings --> */}
            <li>
                <NavLink
                    to="/manage-building"
                    className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${pathname.includes('dashboard') && 'bg-graydark dark:bg-meta-4'
                        }`}
                >
                    <FaRegBuilding />
                    Buildings
                </NavLink>
            </li>
            {/* <!-- Menu Item Buildings --> */}


            {/* <!-- Menu Item Calendar --> */}
            <li>
                <NavLink
                    to="/company-calendar"
                    className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${pathname.includes('calendar') &&
                        'bg-graydark dark:bg-meta-4'
                        }`}
                >
                    <IoCalendarOutline />
                    Reservations
                </NavLink>
            </li>
            {/* <!-- Menu Item Calendar --> */}

            {/* <!-- Menu Item Settings --> */}
            <li>
                <NavLink
                    to="/company-financial-report"
                    className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${pathname.includes('settings') &&
                        'bg-graydark dark:bg-meta-4'
                        }`}
                >
                    <FaRegChartBar />
                    Finacial Report
                </NavLink>
            </li>
            {/* <!-- Menu Item Settings --> */}

            {/* <!-- Menu Item Settings --> */}
            <li>
                <NavLink
                    to="/company-settings"
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

export default CompanySidebar;
