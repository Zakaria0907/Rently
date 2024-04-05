import React, { ChangeEvent, useEffect, useState } from 'react';
import { AssignmentStatus } from '../types/enums';
import { Assignment, User } from '../types/types';
import useAxiosPrivate from '../hooks/useAxiosPrivate';
import toast from 'react-hot-toast';

interface EmployeeRequestPopupProps {
    closeModal: () => void;
    assignmentID: number;
}

const EmployeeRequestPopup: React.FC<EmployeeRequestPopupProps> = ({ closeModal, assignmentID}: EmployeeRequestPopupProps) => {
    // const [status, setStatus] = useState<AssignmentStatus>(assignment?.status || AssignmentStatus.ASSIGNED);
    const [employeeID, setEmployeeID] = useState<number>();
    const [allEmployees, setAllEmployees] = useState<User[]>([]); // Initialized as an empty array

    const axiosPrivate = useAxiosPrivate();

    useEffect(() => {
        const fetchEmployees = async () => {
            try {
                const response = await axiosPrivate.get('/company-admin/employees');
                setAllEmployees(response.data);
            } catch (error) {
                console.error(error);
                toast.error("Network Error");
            }
        };
        fetchEmployees();
    }, [axiosPrivate]);

    async function assignEmployee (): Promise<void> {
        console.log(employeeID);
        console.log(assignmentID);
        console.log("RIGHT HERE");
        try {
            await axiosPrivate.patch(`/company-admin/assignments/assign/employee=${employeeID}/assignment=${assignmentID}`
                // {
                //     status: status,
                //     comment: comment
                // }
            );
            toast.success("Request Updated Successfully");
            closeModal();
        } catch (error) {
            console.log(error);
            toast.error("Network Error!");
        }
    }

    return (
        <div
            className="fixed inset-0 z-9999 overflow-y-auto bg-black bg-opacity-50"
            aria-labelledby="modal-title"
            role="dialog"
            aria-modal="true"
        >
            <div className="flex items-end justify-center min-h-screen px-4 pt-4 pb-20 text-center sm:block sm:p-0">
                <span className="hidden sm:inline-block sm:h-screen sm:align-middle" aria-hidden="true">
                    &#8203;
                </span>

                <div className="relative inline-block px-4 pt-5 pb-4 overflow-hidden text-left align-bottom transition-all transform bg-white rounded-lg shadow-xl sm:my-8 sm:w-full sm:max-w-sm sm:p-6 sm:align-middle">
                    <h3 className="text-lg font-medium leading-6 text-gray-800 capitalize" id="modal-title">
                        Request Details
                    </h3>
                    <p className="mt-2 text-sm text-gray-500">
                        This will add a request to your request list
                    </p>

                    <form className="mt-4" action="#">
                        <div className="flex flex-col gap-5.5 ">
                            <div>
                                <label htmlFor="employeeDropdown" className="mb-3 block text-black dark:text-white">
                                    Select Employee
                                </label>
                                <select
                                    id="employeeDropdown"
                                    onChange={(e: ChangeEvent<HTMLSelectElement>) => {setEmployeeID(parseInt(e.target.value))}}
                                    // onChange={handleEmployeeChange(employee)}
                                    className="w-full rounded-lg border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                >
                                    <option value="">Select an employee</option>
                                    {allEmployees.map((employee: User) => (
                                        <option key={employee.id} value={employee.id}>
                                            {employee.email}
                                        </option>
                                    ))}
                                </select>
                            </div>

                        </div>

                        <div className="mt-4 sm:flex sm:items-center sm:-mx-2">
                            <button
                                type="button"
                                onClick={closeModal}
                                className="w-full px-4 py-2 text-sm font-medium tracking-wide text-gray-700 capitalize transition-colors duration-300 transform border border-gray-200 rounded-md sm:w-1/2 sm:mx-2  hover:bg-gray-100 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-40"
                            >
                                Cancel
                            </button>

                            <button
                                type="button"
                                className={`w-full px-4 py-2 mt-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-primary hover:bg-indigo-500 rounded-md sm:mt-0 sm:w-1/2 sm:mx-2 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40`}
                                onClick={() => { employeeID ? assignEmployee(): toast.error("Selection Error") }}
                            >
                                Submit
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default EmployeeRequestPopup;
