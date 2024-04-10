import React, { useEffect, useState } from "react";
import Breadcrumb from "../../components/Breadcrumbs/Breadcrumb";
import ChartOne from "../../components/Charts/ChartOne";
import ChartTwo from "../../components/Charts/ChartTwo";
import DefaultLayout from "../DefaultLayout";
import { Link } from "react-router-dom";
import { FaRegBuilding, FaRegUser } from "react-icons/fa";
import { BsReceipt } from "react-icons/bs";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";

const OwnerDashboard: React.FC = () => {
  const [cardCollection, setCardCollection] = useState<any[]>([]);
  const axiosPrivate = useAxiosPrivate();

  useEffect(() => {
    const fetchUnitsCount = async () => {
      try {
        const response = await axiosPrivate.get("/occupant/my-condos");
        const unitsCount = response.data.length;
        updateCardCollection(unitsCount);
      } catch (error) {
        console.error("Failed to fetch unit count:", error);
        updateCardCollection(1); // in case of a glitch or somehow an error, the default value is 1
      }
    };

    fetchUnitsCount();
  }, [axiosPrivate]);

  const updateCardCollection = (unitsCount: number) => {
    const cards = [
      {
        title: "Units",
        count: unitsCount, // what's happening here is that im checking how many elements are in the output array of calling the route GET /api/occupant/my-condos
        link: "/manage-units",
        icon: <FaRegBuilding className="mt-1 text-xl" />,
      },
      {
        title: "Renters",
        count: 9,
        link: "/renters",
        icon: <FaRegUser className="mt-1 text-xl" />,
      },
      {
        title: "Unit Associated Fees",
        count: 7,
        link: "/unit-fees",
        icon: <BsReceipt className="mt-1 text-xl" />,
      },
    ];

    setCardCollection(cards);
  };

  return (
    <DefaultLayout>
      <Breadcrumb pageName="Owner Dashboard" />
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
        {cardCollection.map((card, index) => (
          <div
            key={index}
            className="bg-white rounded-md border border-gray-200 p-6 shadow-default shadow-black/5"
          >
            <div className="flex justify-between mb-10">
              <div>
                <div className="flex items-center mb-1">
                  {card.icon}
                  <div className="text-2xl font-semibold ml-2">
                    {card.count}
                  </div>
                </div>
                <div className="text-sm font-medium text-gray-400">
                  {card.title}
                </div>
              </div>
              <div className="dropdown">
                <button
                  type="button"
                  className="dropdown-toggle text-gray-400 hover:text-gray-600"
                >
                  <i className="ri-more-fill"></i>
                </button>
                <ul className="dropdown-menu shadow-md shadow-black/5 z-30 hidden py-1.5 rounded-md bg-white border border-gray-100 w-full max-w-[140px]">
                  <li>
                    <Link
                      to="#"
                      className="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-blue-500 hover:bg-gray-50"
                    >
                      Profile
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="#"
                      className="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-blue-500 hover:bg-gray-50"
                    >
                      Settings
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="#"
                      className="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-blue-500 hover:bg-gray-50"
                    >
                      Logout
                    </Link>
                  </li>
                </ul>
              </div>
            </div>
            <Link
              to={card.link}
              className="text-primary font-medium text-sm hover:text-red-800"
            >
              View
            </Link>
          </div>
        ))}
      </div>
      <div className="grid grid-cols-12 gap-4 md:gap-6 2xl:gap-7.5">
        <ChartOne />
        <ChartTwo />
      </div>
    </DefaultLayout>
  );
};

export default OwnerDashboard;
