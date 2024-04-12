import { NavLink, useLocation } from "react-router-dom";
import { FaRegBuilding, FaRegUser } from "react-icons/fa";
import { RxDashboard } from "react-icons/rx";
import { IoSettingsOutline } from "react-icons/io5";
import { HiOutlineUserGroup } from "react-icons/hi";
import { MdHomeWork } from "react-icons/md";

const CompanySidebar = () => {
  const location = useLocation();
  const { pathname } = location;

  return (
    <ul className="mb-6 flex flex-col gap-1.5">
      {/* <!-- Menu Item Dashboard --> */}
      <li>
        <NavLink
          to="/admin-dashboard"
          className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${
            pathname.includes("calendar") && "bg-graydark dark:bg-meta-4"
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
          to="/admin-profile"
          className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${
            pathname.includes("profile") && "bg-graydark dark:bg-meta-4"
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
          to="/admin-view-buildings"
          className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${
            pathname.includes("dashboard") && "bg-graydark dark:bg-meta-4"
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
          to="/admin-view-owners"
          className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${
            pathname.includes("calendar") && "bg-graydark dark:bg-meta-4"
          }`}
        >
          <HiOutlineUserGroup />
          Owners
        </NavLink>
      </li>
      {/* <!-- Menu Item Calendar --> */}

      {/* <!-- Menu Item Create Company --> */}
      <li>
        <NavLink
          to="/create-company"
          className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${
            pathname.includes("settings") && "bg-graydark dark:bg-meta-4"
          }`}
        >
          <MdHomeWork />
          Create Company
        </NavLink>
      </li>
      {/* <!-- Menu Item Create Company --> */}

      {/* <!-- Menu Item Create Company Admin --> */}
      <li>
        <NavLink
          to="/create-company-admin"
          className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${
            pathname.includes("settings") && "bg-graydark dark:bg-meta-4"
          }`}
        >
          <FaRegUser />
          Create Company Admin
        </NavLink>
      </li>
      {/* <!-- Menu Item Create Company Admin --> */}

      {/* <!-- Menu Item Settings --> */}
      <li>
        <NavLink
          to="/company-settings"
          className={`group relative flex items-center gap-2.5 rounded-sm py-2 px-4 font-medium text-bodydark1 duration-300 ease-in-out hover:bg-graydark dark:hover:bg-meta-4 ${
            pathname.includes("settings") && "bg-graydark dark:bg-meta-4"
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
