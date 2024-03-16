import React, { useState } from 'react';
import { Building } from '../types/types';
import useAxiosPrivate from '../hooks/useAxiosPrivate';

interface EditPropertyPopupProps {
    closeModal: () => void;
    userId: number;
    selectedBuilding: Building;
}

const EditPropertyPopup: React.FC<EditPropertyPopupProps> = ({ closeModal, userId, selectedBuilding }: EditPropertyPopupProps) => {
    const [building, setBuilding] = useState<Building>({
        name: selectedBuilding.name,
        address: selectedBuilding.address,
        unitCount: selectedBuilding.unitCount ?? 0,
        description: selectedBuilding.description,
    });

    const axiosPrivate = useAxiosPrivate();


    const addBuilding = async () => {
        console.log("Adding this building: ", building);
        try {
            await axiosPrivate.patch(`/company/${userId}/create-building`, building);
            closeModal();
        } catch (error) {
            console.log(error);
        }
    }

    const enableAddButton = () => {
        return building.name.length > 0 && building.address.length > 0 && building.description.length > 0;
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
                        Add a new Building
                    </h3>
                    <p className="mt-2 text-sm text-gray-500">
                        This will add a property to your property list
                    </p>

                    <form className="mt-4" action="#">
                        <div className="flex flex-col gap-5.5 ">
                            <div>
                                <label className="mb-3 block text-black dark:text-white">
                                    Building Name
                                </label>
                                <input
                                    type="text"
                                    placeholder="Default Input"
                                    className="w-full rounded-lg border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                    onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                                        setBuilding({ ...building, name: e.target.value })
                                    }
                                    value={building.name}
                                />
                            </div>

                            <div>
                                <label className="mb-3 block text-black dark:text-white">
                                    Address
                                </label>
                                <input
                                    type="text"
                                    placeholder="Default Input"
                                    className="w-full rounded-lg border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                    onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                                        setBuilding({ ...building, address: e.target.value })
                                    }
                                    value={building.address}
                                />
                            </div>

                            <div>
                                <label className="mb-3 block text-black dark:text-white">
                                    building Count
                                </label>
                                <input
                                    type="number"
                                    placeholder="Number of Units"
                                    className="w-full rounded-lg border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                    onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                                        setBuilding({ ...building, unitCount: parseInt(e.target.value) })
                                    }
                                    value={building.unitCount}
                                />
                            </div>

                            <div>
                                <label className="mb-3 block text-black dark:text-white">
                                    Description
                                </label>
                                <textarea
                                    rows={3}
                                    placeholder="Description of the Building"
                                    className="w-full rounded-lg border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                    onChange={(e: React.ChangeEvent<HTMLTextAreaElement>) =>
                                        setBuilding({ ...building, description: e.target.value })
                                    }
                                    value={building.description}
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
                                onClick={() => addBuilding()}
                                className={`w-full px-4 py-2 mt-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform  ${enableAddButton() ? 'bg-primary hover:bg-indigo-500' : 'bg-gray-300 hover:bg-red-500'} rounded-md sm:mt-0 sm:w-1/2 sm:mx-2 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40`}
                            >
                                Add
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default EditPropertyPopup;