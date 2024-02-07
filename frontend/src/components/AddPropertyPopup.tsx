import React, { ChangeEvent, useState } from 'react';

interface AddPropertyPopupProps {
    closeModal: () => void;
    addProperty: (property: any) => void;
}

const AddPropertyPopup: React.FC<AddPropertyPopupProps> = ({closeModal, addProperty} : AddPropertyPopupProps) => {
    const [property, setProperty] = useState({
        id: Math.random().toString(36).substring(2, 15),
        name: "",
        address: "",
    });

    return (
        <div
            className="fixed inset-0 z-10 overflow-y-auto bg-black bg-opacity-50"
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
                        Add a new Property
                    </h3>
                    <p className="mt-2 text-sm text-gray-500">
                        This will add a property to your property list
                    </p>

                    <form className="mt-4" action="#">
                        <label htmlFor="emails-list" className="text-sm text-gray-700">
                            Property Name
                        </label>

                        <label className="block mt-3" htmlFor="email">
                            <input
                                type="email"
                                name="email"
                                id="email"
                                placeholder="Property Name"
                                className="block w-full px-4 py-3 text-sm text-gray-700 bg-white border border-gray-200 rounded-md focus:border-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40"
                                onChange={(e: ChangeEvent<HTMLInputElement>) =>
                                    setProperty({ ...property, name: e.target.value })
                                }
                                value={property.name}
                            />
                        </label>

                        <label htmlFor="emails-list" className="text-sm text-gray-700">
                            Address
                        </label>

                        <label className="block mt-3" htmlFor="email">
                            <input
                                type="email"
                                name="email"
                                id="email"
                                placeholder="1234 Main St, City, State, 12345"
                                className="block w-full px-4 py-3 text-sm text-gray-700 bg-white border border-gray-200 rounded-md focus:border-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40"
                                onChange={(e: ChangeEvent<HTMLInputElement>) =>
                                    setProperty({ ...property, address: e.target.value })
                                }
                                value={property.address}
                            />
                        </label>

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
                                className="w-full px-4 py-2 mt-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-black rounded-md sm:mt-0 sm:w-1/2 sm:mx-2 hover:bg-blue-500 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40"
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

export default AddPropertyPopup;