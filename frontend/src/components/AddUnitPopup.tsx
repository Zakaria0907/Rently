import React, { ChangeEvent, useState } from 'react';
import { FaRegBuilding } from 'react-icons/fa';

interface AddPropertyPopupProps {
    closeModal: () => void;
    addProperty: (property: any) => void;
}

const AddUnitPopup: React.FC<AddPropertyPopupProps> = ({ closeModal, addProperty }: AddPropertyPopupProps) => {
    const [property, setProperty] = useState({
        id: Math.random().toString(36).substring(2, 15),
        name: "",
        address: "",
    });

    const [availableForRent, setAvailableForRent] = useState(false);

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
                        Add a new Unit
                    </h3>
                    <p className="mt-2 text-sm text-gray-500">
                        This will add a property to your property list
                    </p>

                    <form className="mt-4" action="#">
                        <div className="flex flex-col gap-5.5 ">
                            <div>
                                <label className="mb-3 block text-black dark:text-white">
                                    Unit Name
                                </label>
                                <input
                                    type="text"
                                    placeholder="Default Input"
                                    className="w-full rounded-lg border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
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
                                />
                            </div>

                            <div>
                                <label className="mb-3 block text-black dark:text-white">
                                    Unit Number
                                </label>
                                <input
                                    type="number"
                                    placeholder="Number of Units"
                                    className="w-full rounded-lg border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
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
                                ></textarea>
                            </div>


                            <div className='mb-3 block text-black dark:text-white'>
                                Unit Available for Rent
                                <label
                                    htmlFor="toggle1"
                                    className="flex cursor-pointer select-none items-center py-3"
                                >
                                    <div className="relative">
                                        <input
                                            type="checkbox"
                                            id="toggle1"
                                            className="sr-only"
                                            onChange={() => {
                                                setAvailableForRent(!availableForRent);
                                            }}
                                        />
                                        <div className="block h-8 w-14 rounded-full bg-meta-9 dark:bg-[#5A616B]"></div>
                                        <div
                                            className={`absolute left-1 top-1 h-6 w-6 rounded-full bg-white transition ${availableForRent && '!right-1 !translate-x-full !bg-primary dark:!bg-white'
                                                }`}
                                        ></div>
                                    </div>
                                </label>
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
                                onClick={() => addProperty(property)}
                                className="w-full px-4 py-2 mt-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-primary rounded-md sm:mt-0 sm:w-1/2 sm:mx-2 hover:bg-indigo-500 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40"
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

export default AddUnitPopup;