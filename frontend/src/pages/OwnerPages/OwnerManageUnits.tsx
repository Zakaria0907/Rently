import React, { useEffect, useState } from "react";
import Breadcrumb from "../../components/Breadcrumbs/Breadcrumb";
import DefaultLayout from "../DefaultLayout";
import { Link } from "react-router-dom";
import { IoSettingsOutline } from "react-icons/io5";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import axios from "axios";

interface Unit {
  id: number;
  address: string;
  unit_number: number;
  description: string;
  registration_key: string;
  status: string;
  building_id: number;
  parking_id?: number | null;
  locker_id?: number | null;
}

const OwnerManageUnits: React.FC = () => {
  const [units, setUnits] = useState<Unit[]>([]);
  const axiosPrivate = useAxiosPrivate();

  useEffect(() => {
    const fetchUnits = async () => {
      try {
        console.log("Sending request to /occupant/my-condos");
        const response = await axiosPrivate.get("/occupant/my-condos");
        console.log("Response received:", response);
        setUnits(response.data);
      } catch (error) {
        if (axios.isAxiosError(error)) {
          console.error(
            "Failed to fetch units:",
            error.response?.data || error.message
          );
        } else {
          console.error("An unexpected error occurred:", error);
        }
      }
    };

    fetchUnits();
  }, [axiosPrivate]);

  return (
    <DefaultLayout>
      <Breadcrumb pageName="Manage Units" />
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
        {units.map((unit, index) => (
          <div
            key={index}
            className="bg-white rounded-md border border-gray-200 p-6 shadow-default shadow-black/5"
          >
            <div className="flex justify-between mb-10">
              <div>
                <div className="flex items-center mb-1">
                  <div className="text-2xl font-semibold">{`Unit ${unit.unit_number}`}</div>
                </div>
                <div className="text-sm font-medium text-gray-400">
                  {unit.address}
                </div>
              </div>
              <IoSettingsOutline
                className="mt-1.5 text-xl text-primary cursor-pointer hover:text-red-800"
                onClick={() => console.log("Settings Clicked", unit.id)}
              />
            </div>
            <Link
              to={`/manage-unit/${unit.id}`}
              className="text-primary font-medium text-sm hover:text-red-800"
            >
              View
            </Link>
          </div>
        ))}
      </div>
    </DefaultLayout>
  );
};

export default OwnerManageUnits;
