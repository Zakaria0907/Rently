import React, { useState } from "react";
import DefaultLayout from "../pages/DefaultLayout";

const EnterOperationalCosts = () => {
  const [costDetails, setCostDetails] = useState({
    description: "",
    amount: "",
    propertyUnit: "",
    dateIncurred: "",
    category: "",
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setCostDetails((prevDetails) => ({
      ...prevDetails,
      [name]: value,
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log(costDetails);
    // send data to backend from here boys!
  };

  return (
    <DefaultLayout>
      <div className="p-6 rounded-lg shadow-lg bg-white dark:bg-boxdark">
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label
              htmlFor="description"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >
              Description
            </label>
            <input
              type="text"
              id="description"
              name="description"
              onChange={handleChange}
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary focus:border-primary block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
              placeholder="Cost description"
              required
            />
          </div>
          <div className="mb-4">
            <label
              htmlFor="amount"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >
              Amount
            </label>
            <input
              type="number"
              id="amount"
              name="amount"
              onChange={handleChange}
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary focus:border-primary block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
              placeholder="0.00"
              required
            />
          </div>
          <div className="mb-4">
            <label
              htmlFor="propertyUnit"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >
              Property/Unit
            </label>
            <input
              type="text"
              id="propertyUnit"
              name="propertyUnit"
              onChange={handleChange}
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary focus:border-primary block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
              placeholder="Property or Unit ID"
              required
            />
          </div>
          <div className="mb-4">
            <label
              htmlFor="dateIncurred"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >
              Date Incurred
            </label>
            <input
              type="date"
              id="dateIncurred"
              name="dateIncurred"
              onChange={handleChange}
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary focus:border-primary block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
              required
            />
          </div>
          <div className="mb-4">
            <label
              htmlFor="category"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >
              Category
            </label>
            <select
              id="category"
              name="category"
              onChange={handleChange}
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary focus:border-primary block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
            >
              <option value="">Select a category</option>
              <option value="maintenance">Maintenance</option>
              <option value="utilities">Utilities</option>
              <option value="services">Services</option>
              <option value="other">Other</option>
            </select>
          </div>
          <button
            type="submit"
            className="text-white bg-primary hover:bg-primary-dark focus:ring-4 focus:outline-none focus:ring-primary-light font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-primary-dark dark:hover:bg-primary-light dark:focus:ring-primary-dark"
          >
            Submit
          </button>
        </form>
      </div>
    </DefaultLayout>
  );
};

export default EnterOperationalCosts;
