import React from 'react';
import Breadcrumb from '../components/Breadcrumbs/Breadcrumb';
import DefaultLayout from './DefaultLayout';
import PropertyTable from '../components/PropertyTable';
import OwnerProperties from '../components/OwnerProperties';


//DEV ONLY COMPONENT!

const PropertyAndRentals: React.FC = () => {
  return (
    <DefaultLayout>
      <Breadcrumb pageName="Property and Rentals" />

      <div >
       <PropertyTable />
         <OwnerProperties />
      </div>
    </DefaultLayout>
  );
};

export default PropertyAndRentals;
