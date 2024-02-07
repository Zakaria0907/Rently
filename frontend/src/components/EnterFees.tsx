import React, { useState } from 'react';

const CustomDropdown = () => {
    return (
        <div className="grid gap-3 mb-3 grid-cols-2">
            <div className="col-span-2 sm:col-span-1">
                <label className="text-sm text-gray-70">
                    Category
                </label>
                <select
                    id="category"
                    className="block mt-1 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full p-2.5"
                >
                    <option >Select category</option>
                    <option value="PA">Parking</option>
                    <option value="PC">Other</option>
                </select>
            </div>

            <div className="col-span-2 sm:col-span-1">
                <label className="text-sm text-gray-700 ">Price</label>
                <label className="block mt-1">
                    <input
                        type="number"
                        name="fees"
                        id="fees"
                        placeholder="2999"
                        className="block mt-1 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full p-2.5"
                    />
                </label>
            </div>
        </div>
    );
};



const CondoFees: React.FC = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [fields, setFields] = useState([<CustomDropdown key={0} />]);

    const addField = () => {
        setFields(prevFields => [...prevFields, <CustomDropdown key={prevFields.length} />]);
    };

    const openModal = () => {
        setIsOpen(true);
    };

    const closeModal = () => {
        setIsOpen(false);
    };

    return (
        <div className="relative flex justify-center">
            <button onClick={openModal} className="px-6 py-2 mx-auto tracking-wide text-white capitalize transition-colors duration-300 transform bg-blue-600 rounded-md hover:bg-blue-500 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-80">
                Adjust Fees
            </button>

            {isOpen && (
                <div className="fixed inset-0 z-10 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
                    <div className="flex items-end justify-center min-h-screen px-4 pt-4 pb-20 text-center sm:block sm:p-0">
                        <span className="hidden sm:inline-block sm:h-screen sm:align-middle" aria-hidden="true">&#8203;</span>

                        <div className="relative inline-block px-4 pt-5 pb-4 overflow-hidden text-left align-bottom transition-all transform bg-white rounded-lg shadow-xl sm:my-8 sm:w-full sm:max-w-sm sm:p-6 sm:align-middle">
                            <h3 className="text-lg font-medium leading-6 text-gray-800 capitalize" id="modal-title">
                                Fees
                            </h3>
                            <p className="mt-2 text-sm text-gray-500">
                                Your condominium profile has been created.
                                Add or change fees associated with this property.
                            </p>

                            {/* Modal Body */}
                            <form className="p-4 md:p-5">
                                <div className="grid gap-4 mb-4 grid-cols-2">
                                    <div className="block mb-2 col-span-2 sm:col-span-1">
                                        <label htmlFor="emails-list" className="text-sm text-gray-700">
                                            Square Footage
                                        </label>
                                        <label className="block mt-2">
                                            <input type="number" name="sqft" id="sqft" placeholder="800" className="block w-full px-4 py-3 text-sm text-gray-700 bg-white border border-gray-200 rounded-md focus:border-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40" />
                                        </label>
                                    </div>

                                    <div className="col-span-2 sm:col-span-1">
                                        <label className="text-sm text-gray-700">
                                            Price
                                        </label>
                                        <input type="number" name="fees" id="fees" placeholder="2999" className="block mt-2 w-full px-4 py-3 text-sm text-gray-700 bg-white border border-gray-200 rounded-md focus:border-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40" />
                                    </div>
                                </div>


                                {/* Additional email input fields */}

                                <button type="button" onClick={addField} className="mt-2 flex items-center rounded py-1.5 px-2 text-sm text-blue-600 transition-colors duration-300 hover:text-blue-400 focus:outline-none">
                                    <svg xmlns="http://www.w3.org/2000/svg" className="w-4 h-4" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
                                        <path strokeLinecap="round" strokeLinejoin="round" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
                                    </svg>
                                    <span className="mx-2">Add another</span>
                                </button>

                                {fields.map(field => field)}

                                <div className="mt-4 sm:flex sm:items-center sm:-mx-2">
                                    <button type="button" onClick={closeModal} className="w-full px-4 py-2 text-sm font-medium tracking-wide text-gray-700 capitalize transition-colors duration-300 transform border border-gray-200 rounded-md sm:w-1/2 sm:mx-2  hover:bg-gray-100 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-40">
                                        Cancel
                                    </button>

                                    <button type="button" className="w-full px-4 py-2 mt-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-blue-600 rounded-md sm:mt-0 sm:w-1/2 sm:mx-2 hover:bg-blue-500 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40">
                                        Send invites
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default CondoFees;