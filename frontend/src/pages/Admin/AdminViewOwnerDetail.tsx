import React, { Component } from 'react';
import condo1 from '../../images/condos/condo1.jpg';
import condo2 from '../../images/condos/condo2.jpg';
import condo3 from '../../images/condos/condo3.jpg';
import condo4 from '../../images/condos/condo4.jpg';
import UserOne from '../../images/user/user-01.png';

// Mock data combined within the component
const mockData = {
  ownerDetails: {
    name: 'John Doe',
    email: 'john.doe@example.com',
    phone: '123-456-7890',
    bio: 'Experienced condo owner with multiple properties in the city. Dedicated to providing excellent accommodations.',
    photoUrl: UserOne,
  },
  condoListings: [
    {
      id: 'listing1',
      title: 'Modern Condo in Downtown',
      location: 'Downtown, City Name',
      price: '1200',
      bedrooms: 2,
      bathrooms: 2,
      squareFeet: 1200,
      amenities: ['Gym', 'Pool', 'Parking'],
      photos: [condo1, condo2],
      available: true,
    },
    {
      id: 'listing2',
      title: 'Cozy Studio Near the Beach',
      location: 'Beachside, City Name',
      price: '900',
      bedrooms: 1,
      bathrooms: 1,
      squareFeet: 800,
      amenities: ['Beach Access', 'Balcony'],
      photos: [condo3, condo4],
      available: false,
    },
  ],
  reviews: [
    {
      id: 'review1',
      reviewerName: 'Alex Smith',
      date: '2023-08-01',
      rating: 4.9,
      comment: 'Great experience, the condo was exactly as described and the owner was very responsive.',
    },
    {
      id: 'review2',
      reviewerName: 'Jamie Doe',
      date: '2023-07-15',
      rating: 4.2,
      comment: 'Lovely place and convenient location. Had a small issue with the WiFi, but it was resolved quickly.',
    },
  ],
};

class AdminViewOwnerDetail extends Component {
    render() {
        const { ownerDetails, condoListings, reviews } = mockData;
    
        return (
          <div className="container mx-auto px-4">
            <div className="bg-white shadow overflow-hidden sm:rounded-lg my-6">
              <div className="px-4 py-5 sm:px-6 flex items-center space-x-4">
                <img className="h-24 w-24 rounded-full object-cover" src={ownerDetails.photoUrl} alt="Owner" />
                <div>
                  <h3 className="text-lg leading-6 font-medium text-gray-900">{ownerDetails.name}</h3>
                  <p className="mt-1 max-w-2xl text-sm text-gray-500">{ownerDetails.email} | {ownerDetails.phone}</p>
                  <p className="mt-1 max-w-2xl text-sm text-gray-500">{ownerDetails.bio}</p>
                </div>
              </div>
            </div>
    
            <div className="my-6">
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
            </div>
    
            <div className="my-6">
              <h2 className="text-2xl font-semibold text-gray-900 mb-4">Reviews</h2>
              {reviews.map((review, index) => (
                <div key={index} className="bg-white shadow overflow-hidden sm:rounded-lg p-4 mb-4">
                  <h4 className="text-lg font-medium text-gray-900">{review.reviewerName} - {review.rating} Stars</h4>
                  <p className="text-sm text-gray-500">Date: {review.date}</p>
                  <p className="mt-2 text-sm text-gray-500">{review.comment}</p>
                </div>
              ))}
            </div>
          </div>
        );
      }
    }

export default AdminViewOwnerDetail;