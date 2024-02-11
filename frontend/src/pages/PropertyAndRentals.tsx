import React from 'react';
import Breadcrumb from '../components/Breadcrumbs/Breadcrumb';
import DefaultLayout from './DefaultLayout';
import PropertyTable from '../components/PropertyTable';


//DEV ONLY COMPONENT!

const PropertyAndRentals: React.FC = () => {
  return (
    <DefaultLayout>
      <Breadcrumb pageName="Property and Rentals" />

      <div >
       <PropertyTable />
      </div>
    </DefaultLayout>
  );
};

export default PropertyAndRentals;
