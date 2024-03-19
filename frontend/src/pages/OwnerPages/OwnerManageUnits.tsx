import React from 'react';
import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import DefaultLayout from '../DefaultLayout';
import { Link } from 'react-router-dom';
import { IoSettingsOutline } from "react-icons/io5";
import { FaRegBuilding } from "react-icons/fa";



const OwnerManageUnits: React.FC = () => {
    const [cardCollection, setCardCollection] = React.useState<any[]>([]);
    // const [units, setUnits] = React.useState<any[]>([]);


    React.useEffect(() => {
        const cards = [
            {
                unitId: 1,
                title: 'Unit 1',
                squareFootage: 1000,
                link: '/manage-unit/1',
                icon: <FaRegBuilding className="mt-1.5 text-xl" />
            },
            {
                unitId: 2,
                title: 'Unit 2',
                squareFootage: 800,
                link: '/manage-unit/2',
                icon: <FaRegBuilding className="mt-1.5 text-xl" />
            },
            {
                unitId: 3,
                title: 'Unit 3',
                squareFootage: 1200,
                link: '/manage-unit/3',
                icon: <FaRegBuilding className="mt-1.5 text-xl" />
            },
            {
                unitId: 4,
                title: 'Unit 4',
                squareFootage: 900,
                link: '/manage-unit/4',
                icon: <FaRegBuilding className="mt-1.5 text-xl" />
            },
            {
                unitId: 5,
                title: 'Unit 5',
                squareFootage: 1100,
                link: '/manage-unit/5',
                icon: <FaRegBuilding className="mt-1.5 text-xl" />
            },
            {
                unitId: 6,
                title: 'Unit 6',
                squareFootage: 950,
                link: '/manage-unit/6',
                icon: <FaRegBuilding className="mt-1.5 text-xl" />
            },

        ];

        setCardCollection(cards);
    }, []);

    const handleSettingsClick = () => {
        console.log('Settings Clicked');
    }

    return (
        <DefaultLayout>
            <Breadcrumb pageName={`Manage Units`} />

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
                {
                    cardCollection.map((card, index) => {
                        return (
                            <div key={index} className="bg-white rounded-md border border-gray-200 p-6 shadow-default shadow-black/5 ">
                                <div className="flex justify-between mb-10">
                                    <div>
                                        <div className="flex items-center mb-1">
                                            <div className="text-2xl font-semibold">{card.title}</div>
                                        </div>
                                        <div className="text-sm font-medium text-gray-400">{card.squareFootage} sq ft </div>
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
                                    <IoSettingsOutline className="mt-1.5 text-xl text-primary cursor-pointer hover:text-red-800" onClick={() => handleSettingsClick()} />
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

export default OwnerManageUnits;
