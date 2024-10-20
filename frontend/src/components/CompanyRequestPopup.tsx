import React, { ChangeEvent } from 'react';
import { AssignmentStatus } from '../types/enums';
import { Assignment } from '../types/types';

interface CompanyRequestPopupProps {
    closeModal: () => void;
    onClickEvent: (assignmentId: number, status: AssignmentStatus, comment: string) => Promise<void>;
    assignment: Assignment | null;
}

const CompanyRequestPopup: React.FC<CompanyRequestPopupProps> = ({ closeModal, onClickEvent, assignment }: CompanyRequestPopupProps) => {

    const [status, setStatus] = React.useState<AssignmentStatus>(assignment?.status || AssignmentStatus.ASSIGNED);
    const [comment, setComment] = React.useState<string>("");

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
                                <label className="mb-3 block text-black dark:text-white">
                                    Request Status
                                </label>
                                <select
                                    id="dropdown"
                                    value={status}
                                    onChange={(e: ChangeEvent<HTMLSelectElement>) => { setStatus(e.target.value as AssignmentStatus) }}
                                    className="w-full rounded-lg border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                >
                                    {Object.keys(AssignmentStatus).map((status, index) => {
                                        return <option key={index}>{status}</option>
                                    })}
                                </select>
                            </div>

                            <div>
                                <label className="mb-3 block text-black dark:text-white">
                                    Comment
                                </label>
                                <textarea
                                    rows={3}
                                    placeholder="Description of the request..."
                                    className="w-full rounded-lg border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                    onChange={(e) => setComment(e.target.value)}
                                ></textarea>
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
                                onClick={() => { onClickEvent(assignment?.id || 0, status, comment) }}
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

export default CompanyRequestPopup;