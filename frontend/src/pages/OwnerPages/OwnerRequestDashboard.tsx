import React, { useEffect, useState } from "react";
import Breadcrumb from "../../components/Breadcrumbs/Breadcrumb";
import DefaultLayout from "../DefaultLayout";
import RequestPopup from "../../components/RequestPopupProps";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import toast, { Toaster } from "react-hot-toast";
import { Request } from "../../types/types";
// import { AssignmentStatus } from "../../types/enums";

const OwnerRequestsDashboard: React.FC = () => {
  const [showRequestPopup, setShowRequestPopup] = useState<boolean>(false);
  const [requests, setRequests] = useState<Request[]>([]);
  const axiosPrivate = useAxiosPrivate();

  useEffect(() => {
    fetchRequests();
  }, [showRequestPopup]);

  const fetchRequests = async () => {
    try {
      const response = await axiosPrivate.get("/occupant/request");
      setRequests(response.data);
    } catch (error) {
      console.error("Failed to fetch requests:", error);
      toast.error("Failed to fetch requests");
    }
  };
  // Omar -> I will keep this in case we ever wanted to add status!
  //   const renderStatus = (status: AssignmentStatus): JSX.Element => {
  //     console.log("This is the status habibi: ", status);
  //     switch (status) {
  //       case AssignmentStatus.ASSIGNED:
  //         return (
  //           <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-semibold text-blue-700 bg-blue-100">
  //             Assigned
  //           </span>
  //         );
  //       case AssignmentStatus.IN_PROGRESS:
  //         return (
  //           <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-semibold text-yellow-700 bg-yellow-100">
  //             In Progress
  //           </span>
  //         );
  //       case AssignmentStatus.COMPLETED:
  //         return (
  //           <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-semibold text-green-700 bg-green-100">
  //             Completed
  //           </span>
  //         );
  //       case AssignmentStatus.CANCELLED:
  //         return (
  //           <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-semibold text-red-700 bg-red-100">
  //             Cancelled
  //           </span>
  //         );
  //       case AssignmentStatus.NOT_ASSIGNED:
  //         return (
  //           <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-semibold text-gray-700 bg-gray-100">
  //             Not Assigned
  //           </span>
  //         );
  //       default:
  //         return (
  //           <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-semibold text-gray-700 bg-gray-100">
  //             Unknown
  //           </span>
  //         );
  //     }
  //   };

  const refreshRequests = async () => {
    // so once we create a new request and the popup closes, the page will be refreshed to show the new request.
    fetchRequests();
  };

  return (
    <DefaultLayout>
      <Breadcrumb pageName="Requests" />
      <Toaster position="top-right" />
      <div className="flex justify-end mb-4">
        <button
          onClick={() => setShowRequestPopup(true)}
          className="btn-primary"
        >
          Create New Request
        </button>
      </div>
      <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
        <table className="w-full text-sm text-left text-gray-500">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
              <th scope="col" className="py-3 px-6">
                Request ID
              </th>
              <th scope="col" className="py-3 px-6">
                Description
              </th>
              <th scope="col" className="py-3 px-6">
                Work Type
              </th>
            </tr>
          </thead>
          <tbody>
            {requests.map((request) => (
              <tr
                key={request.id}
                className="bg-white border-b hover:bg-gray-50"
              >
                <td className="py-4 px-6">{request.id}</td>
                <td className="py-4 px-6">{request.request_description}</td>
                <td className="py-4 px-6">{request.work_type}</td>
                {/* <td className="py-4 px-6">{renderStatus(request.status)}</td>    will keep this in case we ever wanted to add status!*/}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {showRequestPopup && (
        <RequestPopup
          onClose={() => setShowRequestPopup(false)}
          refreshRequests={fetchRequests}
        />
      )}
    </DefaultLayout>
  );
};

export default OwnerRequestsDashboard;
