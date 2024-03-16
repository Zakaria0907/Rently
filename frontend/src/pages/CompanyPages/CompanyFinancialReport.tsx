import React from 'react';
import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import ChartOne from '../../components/Charts/ChartOne';
import ChartTwo from '../../components/Charts/ChartTwo';
import ChartThree from '../../components/Charts/ChartThree';
import DefaultLayout from '../DefaultLayout';
import { Link } from 'react-router-dom';
import ChartFour from '../../components/Charts/ChartFour';


const CompanyFinancialReport: React.FC = () => {

    return (
        <DefaultLayout>
            <Breadcrumb pageName="Financial Report" />
            <div className="grid grid-cols-12 gap-4 md:gap-6 2xl:gap-7.5">
                <ChartOne />
                <ChartTwo />
                <ChartThree/>
                <ChartFour/>
            </div>
        </DefaultLayout>
    );
};

export default CompanyFinancialReport;
