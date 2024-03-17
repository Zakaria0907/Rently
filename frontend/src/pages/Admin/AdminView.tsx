import React from 'react';
import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import ChartOne from '../../components/Charts/ChartOne';
import ChartTwo from '../../components/Charts/ChartTwo';
import DefaultLayout from '../DefaultLayout';
import { Link } from 'react-router-dom';
import { GrUserWorker } from "react-icons/gr";
import { FaRegBuilding, FaRegUser } from "react-icons/fa";


const AdminView: React.FC = () => {
    const [cardCollection, setCardCollection] = React.useState<any[]>([]);

    React.useEffect(() => {
        const cards = [
            {
                title: 'Buildings',
                count: 2,
                link: '/admin-view-buildings',
                icon: <FaRegBuilding className="mt-1 text-xl" />,
            },
            {
                title: 'Owners',
                count: 10,
                link: '/admin-view-owners',
                icon: <FaRegUser className="mt-1 text-xl" />,
            },
            {
                title: 'Employees',
                count: 7,
                link: '/employees',
                icon: <GrUserWorker className="mt-1 text-xl" />,
            },
        ];

        setCardCollection(cards);
    }, []);

    return (
        <DefaultLayout>
            <Breadcrumb pageName="Admin View" />
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
                {
                    cardCollection.map((card, index) => {
                        return (
                            <div key={index} className="bg-white rounded-md border border-gray-200 p-6 shadow-default shadow-black/5">
                                <div className="flex justify-between mb-10">
                                    <div>
                                        <div className="flex items-center mb-1">
                                            <div className="text-2xl font-semibold">{card.count}</div>
                                        </div>
                                        <div className="text-sm font-medium text-gray-400">{card.title}</div>
                                    </div>
                                    <div className="dropdown">
                                        <button type="button" className="dropdown-toggle text-gray-400 hover:text-gray-600"><i className="ri-more-fill"></i></button>
                                        <ul className="dropdown-menu shadow-md shadow-black/5 z-30 hidden py-1.5 rounded-md bg-white border border-gray-100 w-full max-w-[140px]">
                                            <li>
                                                <Link to="#" className="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-blue-500 hover:bg-gray-50">Profile</Link>
                                            </li>
                                            <li>
                                                <Link to="#" className="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-blue-500 hover:bg-gray-50">Settings</Link>
                                            </li>
                                            <li>
                                                <Link to="#" className="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-blue-500 hover:bg-gray-50">Logout</Link>
                                            </li>
                                        </ul>
                                    </div>
                                    {card.icon}
                                </div>
                                <Link to={card.link} className="text-primary font-medium text-sm hover:text-red-800">View</Link>
                            </div>
                        );
                    })
                }
            </div>
            <div className="grid grid-cols-12 gap-4 md:gap-6 2xl:gap-7.5">
                {/* <ChartOne />
                <ChartTwo /> */}
            </div>
        </DefaultLayout>
    );
};

export default AdminView;
