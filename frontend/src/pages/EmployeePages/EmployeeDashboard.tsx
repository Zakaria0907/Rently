import React from 'react';
import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import DefaultLayout from '../DefaultLayout';
import { Link } from 'react-router-dom';
import { FaRegUser } from "react-icons/fa";
import { BsReceipt } from "react-icons/bs";


const EmployeeDashboard: React.FC = () => {
    const [cardCollection, setCardCollection] = React.useState<any[]>([]);

    React.useEffect(() => {
        const cards = [
            {
                title: 'Manage Requests',
                count: 10,
                link: '/employee-request-dashboard',
                icon: <BsReceipt className="mt-1 text-xl" />,
            },
            {
                title: 'Random',
                count: 9,
                link: '/employee-request-dashboard',
                icon: <FaRegUser className="mt-1 text-xl" />,
            },
            {
                title: 'Random',
                count: 7,
                link: '/employee-request-dashboard',
                icon: <BsReceipt className="mt-1 text-xl" />,
            },
        ];

        setCardCollection(cards);
    }, []);

    return (
        <DefaultLayout>
            <Breadcrumb pageName="Renter Dashboard" />
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
        </DefaultLayout>
    );
};

export default EmployeeDashboard;
