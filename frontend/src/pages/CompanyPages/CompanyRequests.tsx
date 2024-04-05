import React, { useEffect, useState } from 'react';
import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import DefaultLayout from '../DefaultLayout';
import { Avatar } from '@chakra-ui/react';
import CompanyRequestPopup from '../../components/CompanyRequestPopup';
import { Assignment } from '../../types/types';
import useAxiosPrivate from '../../hooks/useAxiosPrivate';
import toast, { Toaster } from 'react-hot-toast';
import { AssignmentStatus } from '../../types/enums';
import useAuth from '../../hooks/useAuth';
import EmployeeRequestPopup from '../../components/EmployeeRequestPopup';



const EmployeeRequestDashboard: React.FC = () => {
    const [requestPopup, setRequestPopup] = useState<boolean>(false);
    const [assignments, setAssignments] = useState<Assignment[]>([]);
    const [selectedAssignmentID, setSelectedAssignmentID] = useState<number>(0);

    

    const axiosPrivate = useAxiosPrivate();
    const { auth } = useAuth();

    useEffect(() => {
        try {
            fetchAssignments();
        } catch (error) {
            console.log(error);
            toast.error("Network Error");
        }
    }, []);

    useEffect(() => {    
        fetchAssignments();
    }, [requestPopup]);


    async function fetchAssignments(): Promise<void> {
        try {
            const response = await axiosPrivate.get('/company-admin/assignments');
            setAssignments(response.data);
        } catch (error) {
            console.log(error);
            toast.error("Network Error");
        }
    }

    function toggleRequestPopup(): void {
        setRequestPopup(!requestPopup);
    }

    async function updateAssignments(assignmentId: number, status: AssignmentStatus, comment: string): Promise<void> {
        try {
            await axiosPrivate.post(`/company-admin/assignments/id=${assignmentId}`,
                {
                    status: status,
                    comment: comment
                }
            );
            toast.success("Request Updated Successfully");
            toggleRequestPopup();
        } catch (error) {
            console.log(error);
            toast.error("Network Error!");
        }
    }


    function renderStatus(status: AssignmentStatus): JSX.Element {
        switch (status) {
            case AssignmentStatus.ASSIGNED:
                return (
                    <div className="inline-flex items-center px-3 py-1 rounded-full gap-x-2 text-blue-500 bg-blue-100/60 dark:bg-gray-800">
                        <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M4.5 7L2 4.5M2 4.5L4.5 2M2 4.5H8C8.53043 4.5 9.03914 4.71071 9.41421 5.08579C9.78929 5.46086 10 5.96957 10 6.5V10" stroke="#667085" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
                        </svg>

                        <h2 className="text-sm font-normal">Assigned</h2>
                    </div>
                );
            case AssignmentStatus.IN_PROGRESS:
                return (
                    <div className="inline-flex items-center px-3 py-1 rounded-full gap-x-2 text-yellow-500 bg-yellow-100/60 dark:bg-gray-800">
                        <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M6 2V10" stroke="#667085" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
                            <path d="M2 6H10" stroke="#667085" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
                        </svg>

                        <h2 className="text-sm font-normal">In Progress</h2>
                    </div>
                );
            case AssignmentStatus.COMPLETED:
                return (
                    <div className="inline-flex items-center px-3 py-1 rounded-full gap-x-2 text-emerald-500 bg-emerald-100/60 dark:bg-gray-800">
                        <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://
                        .org/2000/svg">
                            <path d="M10 3L4.5 8.5L2 6" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
                        </svg>
                        <h2 className="text-sm font-normal">Completed</h2>
                    </div>
                )
            case AssignmentStatus.CANCELLED:
                return (
                    <div className="inline-flex items-center px-3 py-1 rounded-full gap-x-2 text-red-500 bg-red-100/60 dark:bg-gray-800">
                        <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M9 3L3 9M3 3L9 9" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
                        </svg>

                        <h2 className="text-sm font-normal">Cancelled</h2>
                    </div>
                );
            case AssignmentStatus.NOT_ASSIGNED:
                return (
                    <div className="inline-flex items-center px-3 py-1 rounded-full gap-x-2 text-gray-500 bg-gray-100/60 dark:bg-gray-800">
                        <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M6 2V10" stroke="#667085" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
                            <path d="M2 6H10" stroke="#667085" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
                        </svg>

                        <h2 className="text-sm font-normal">Not Assigned</h2>
                    </div>
                );
            default:
                return <></>;
        }
    }

    function checkStatus(status: AssignmentStatus): boolean {
        return status === AssignmentStatus.COMPLETED || status === AssignmentStatus.CANCELLED;
    }
    
//assignEmployee(assignment.employee_id, assignment.id)
    return (
        <DefaultLayout>

            <Toaster position="top-right" />
            <Breadcrumb pageName="Manage Requests" />
            <div className="h-full w-full">

                <section className="container px-4 mx-auto">
                    <div className="flex flex-col">
                        <div className="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                            <div className="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                                <div className="overflow-hidden border border-gray-200 dark:border-gray-700 md:rounded-lg">
                                    <table className="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
                                        <thead className="bg-gray-50 dark:bg-gray-800">
                                            <tr>
                                                <th scope="col" className="py-3.5 px-4 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    <div className="flex items-center gap-x-3">
                                                        <input type="checkbox" className="text-primary border-gray-300 rounded dark:bg-gray-900 dark:ring-offset-gray-900 dark:border-gray-700" />
                                                        <button className="flex items-center gap-x-2">
                                                            <span>Request ID</span>

                                                            <svg className="h-3" viewBox="0 0 10 11" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                                <path d="M2.13347 0.0999756H2.98516L5.01902 4.79058H3.86226L3.45549 3.79907H1.63772L1.24366 4.79058H0.0996094L2.13347 0.0999756ZM2.54025 1.46012L1.96822 2.92196H3.11227L2.54025 1.46012Z" fill="currentColor" stroke="currentColor" strokeWidth="0.1" />
                                                                <path d="M0.722656 9.60832L3.09974 6.78633H0.811638V5.87109H4.35819V6.78633L2.01925 9.60832H4.43446V10.5617H0.722656V9.60832Z" fill="currentColor" stroke="currentColor" strokeWidth="0.1" />
                                                                <path d="M8.45558 7.25664V7.40664H8.60558H9.66065C9.72481 7.40664 9.74667 7.42274 9.75141 7.42691C9.75148 7.42808 9.75146 7.42993 9.75116 7.43262C9.75001 7.44265 9.74458 7.46304 9.72525 7.49314C9.72522 7.4932 9.72518 7.49326 9.72514 7.49332L7.86959 10.3529L7.86924 10.3534C7.83227 10.4109 7.79863 10.418 7.78568 10.418C7.77272 10.418 7.73908 10.4109 7.70211 10.3534L7.70177 10.3529L5.84621 7.49332C5.84617 7.49325 5.84612 7.49318 5.84608 7.49311C5.82677 7.46302 5.82135 7.44264 5.8202 7.43262C5.81989 7.42993 5.81987 7.42808 5.81994 7.42691C5.82469 7.42274 5.84655 7.40664 5.91071 7.40664H6.96578H7.11578V7.25664V0.633865C7.11578 0.42434 7.29014 0.249976 7.49967 0.249976H8.07169C8.28121 0.249976 8.45558 0.42434 8.45558 0.633865V7.25664Z" fill="currentColor" stroke="currentColor" strokeWidth="0.3" />
                                                            </svg>
                                                        </button>
                                                    </div>
                                                </th>

                                                <th scope="col" className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    Date
                                                </th>

                                                <th scope="col" className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    Status
                                                </th>

                                                <th scope="col" className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    Employee
                                                </th>

                                                <th scope="col" className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    Request Type
                                                </th>

                                                <th scope="col" className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">

                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody className="bg-white divide-y divide-gray-200 dark:divide-gray-700 dark:bg-gray-900">
                                            {
                                                assignments.map((assignment: Assignment) => (
                                                    <tr key={assignment.id}>
                                                        <td className="px-4 py-4 text-sm font-medium text-gray-700 dark:text-gray-200 whitespace-nowrap">
                                                            <div className="inline-flex items-center gap-x-3">
                                                                <input type="checkbox" className="text-primary border-gray-300 rounded dark:bg-gray-900 dark:ring-offset-gray-900 dark:border-gray-700" />

                                                                <span>#{assignment.id}</span>
                                                            </div>
                                                        </td>
                                                        <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">Jan 6, 2022</td>
                                                        <button onClick={() => {
                                                                    setSelectedAssignmentID(assignment.id)
                                                                    toggleRequestPopup();
                                                                }
                                                                } 
                                                                className={`${checkStatus(assignment.status) ? 'text-gray-400' : 'text-primary transition-colors duration-200 hover:text-indigo-500'}  focus:outline-none`}
                                                                disabled={checkStatus(assignment.status)}
                                                        >
                                                            <td className="px-4 py-4 text-sm font-medium text-gray-700 whitespace-nowrap">
                                                                {renderStatus(assignment.status)}
                                                            </td>
                                                            
                                                        </button>
                                                        <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                            <div className="flex items-center gap-x-2">
                                                                <Avatar size={'sm'} />
                                                                <div>
                                                                    <div>
                                                                        {assignment.status == AssignmentStatus.ASSIGNED ? <h2 className="text-sm font-medium text-gray-800 dark:text-white "> {auth.firstname + ' ' + auth.lastname} </h2> : 'Not Assigned'}
                                                                    </div>
                                                                    {assignment.status == AssignmentStatus.ASSIGNED ? <p className="text-xs font-normal text-gray-600 dark:text-gray-400">{auth.email}</p> : 'No email'}
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">{assignment.work_type}</td>
                                                    </tr>
                                                ))
                                            }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </section>

            </div>

            {requestPopup && <EmployeeRequestPopup closeModal={toggleRequestPopup} assignmentID={selectedAssignmentID} />}
        </DefaultLayout>
    );
};

export default EmployeeRequestDashboard;


