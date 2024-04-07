import { Avatar } from '@chakra-ui/react';
import React, { Component } from 'react';
import { useLocation } from 'react-router-dom';
import DefaultLayout from '../DefaultLayout';


const CompanyEmployeeDetail = () => {
  const location = useLocation();
  const employee = location.state?.employee; 

  return (
      <DefaultLayout>
          {employee && (
              <div className="container mx-auto px-4">
              <div className="bg-white shadow overflow-hidden sm:rounded-lg my-6">
                <div className="px-4 py-5 sm:px-6 flex items-center space-x-4">
                  {/* <img className="h-24 w-24 rounded-full object-cover" src={ownerDetails.photoUrl} alt="Owner" /> */}
                  <Avatar size={'sm'}/>
                  <div>
                    <h3 className="text-lg leading-6 font-medium text-gray-900">{employee.name}</h3>
                    <p className="mt-1 max-w-2xl text-sm text-gray-500">{employee.email} | {employee.phone}</p>
                    <p className="mt-1 max-w-2xl text-sm text-gray-500">{employee.bio}</p>
                  </div>
                </div>
              </div>
      
              {/* <div className="my-6">
                <h2 className="text-2xl font-semibold text-gray-900 mb-4">Condo Listings</h2>
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
                      {condoListings.map((listing, index) => (
                          <div key={index} className="bg-white shadow overflow-hidden sm:rounded-lg p-4">
                              <div className="mb-4">
                                  <img className="w-full h-48 object-cover rounded-md" src={listing.photos[0]} alt="Listing" />
                              </div>
                              <h3 className="text-lg leading-6 font-medium text-gray-900">{listing.title}</h3>
                              <p className="mt-2 text-sm text-gray-500">{listing.location}</p>
                              <p className="mt-1 text-sm text-gray-500">${listing.price} per month</p>
                              <div className="mt-4">
                                  <span className={`inline-flex items-center px-3 py-0.5 rounded-full text-sm font-medium ${listing.available ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}`}>
                                  {listing.available ? 'Available' : 'Not Available'}
                                  </span>
                              </div>
                          </div>
                      ))}
                  </div>
                  <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
                      {allEmployees.map((listing, index) => (
                          <div key={index} className="bg-white shadow overflow-hidden sm:rounded-lg p-4">
                              <div className="mb-4">
                                  <img className="w-full h-48 object-cover rounded-md" src={listing.photos[0]} alt="Listing" />
                              </div>
                              <h3 className="text-lg leading-6 font-medium text-gray-900">{listing.title}</h3>
                              <p className="mt-2 text-sm text-gray-500">{listing.location}</p>
                              <p className="mt-1 text-sm text-gray-500">${listing.price} per month</p>
                              <div className="mt-4">
                                  <span className={`inline-flex items-center px-3 py-0.5 rounded-full text-sm font-medium ${listing.available ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}`}>
                                  {listing.available ? 'Available' : 'Not Available'}
                                  </span>
                              </div>
                          </div>
                      ))}
                  </div>
              </div> */}
      
              {/* <div className="my-6">
                <h2 className="text-2xl font-semibold text-gray-900 mb-4">Reviews</h2>
                {reviews.map((review, index) => (
                  <div key={index} className="bg-white shadow overflow-hidden sm:rounded-lg p-4 mb-4">
                    <h4 className="text-lg font-medium text-gray-900">{review.reviewerName} - {review.rating} Stars</h4>
                    <p className="text-sm text-gray-500">Date: {review.date}</p>
                    <p className="mt-2 text-sm text-gray-500">{review.comment}</p>
                  </div>
                ))}
              </div> */}
            </div>
          )}
      </DefaultLayout>
  );
};


export default CompanyEmployeeDetail;