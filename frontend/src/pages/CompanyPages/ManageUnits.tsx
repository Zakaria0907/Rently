import React, { useEffect } from 'react';
import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import DefaultLayout from '../DefaultLayout';
import { Link } from 'react-router-dom';
import { IoSettingsOutline } from "react-icons/io5";
import { IoIosAddCircleOutline } from 'react-icons/io';
import { useParams } from 'react-router-dom';
import AddUnitPopup from '../../components/AddUnitPopup';
import useAuth from '../../hooks/useAuth';
import { Unit } from '../../types/types';
import useAxiosPrivate from '../../hooks/useAxiosPrivate';
import EditUnitPopup from '../../components/EditUnitPopup';



const ManageUnits: React.FC = () => {
    // const [cardCollection, setCardCollection] = React.useState<any[]>([]);
    const { buildingId } = useParams<{ buildingId: string }>();
    const [units, setUnits] = React.useState<Unit[]>([]);
    const [addUnitPopup, setAddUnitPopup] = React.useState(false);
    const [editUnitPopup, setEditUnitPopup] = React.useState(false);
    const [selectedUnit, setSelectedUnit] = React.useState<Unit | undefined>(undefined);
    const { auth } = useAuth();
    const axiosPrivate = useAxiosPrivate();




    useEffect(() => {
        fetchUnits();
    }, [addUnitPopup]);

    useEffect(() => {
        fetchUnits();
    }, []);

    const fetchUnits = async () => {
        try {
            const response = await axiosPrivate.get(`/company/building/${buildingId}/condos/all`);
            console.log("Units fetched: ", response.data);
            setUnits(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    
    const toggleAddUnitPopup = () => {
        setAddUnitPopup(((prev) => !prev));
    };

    const handleSettingsClick = (unit: Unit) => {
        console.log('Settings Clicked');
        setSelectedUnit(unit);
        setEditUnitPopup(true);
    }


    // useEffect(() => {
    //     const cards = [
    //         {
    //             unitId: 1,
    //             title: 'Unit 1',
    //             squareFootage: 1000,
    //             link: '/manage-unit/1',
    //             icon: <FaRegBuilding className="mt-1.5 text-xl" />
    //         },
    //         {
    //             unitId: 2,
    //             title: 'Unit 2',
    //             squareFootage: 800,
    //             link: '/manage-unit/2',
    //             icon: <FaRegBuilding className="mt-1.5 text-xl" />
    //         },
    //         {
    //             unitId: 3,
    //             title: 'Unit 3',
    //             squareFootage: 1200,
    //             link: '/manage-unit/3',
    //             icon: <FaRegBuilding className="mt-1.5 text-xl" />
    //         },
    //         {
    //             unitId: 4,
    //             title: 'Unit 4',
    //             squareFootage: 900,
    //             link: '/manage-unit/4',
    //             icon: <FaRegBuilding className="mt-1.5 text-xl" />
    //         },
    //         {
    //             unitId: 5,
    //             title: 'Unit 5',
    //             squareFootage: 1100,
    //             link: '/manage-unit/5',
    //             icon: <FaRegBuilding className="mt-1.5 text-xl" />
    //         },
    //         {
    //             unitId: 6,
    //             title: 'Unit 6',
    //             squareFootage: 950,
    //             link: '/manage-unit/6',
    //             icon: <FaRegBuilding className="mt-1.5 text-xl" />
    //         },

    //     ];

    //     setCardCollection(cards);
    // }, []);


    return (
        <DefaultLayout>
            <Breadcrumb pageName={`Manage Units | Building ${buildingId}`} />
            <div className="flex items-center ">
                <button
                    className="flex items-center bg-primary text-white rounded-md px-3 py-2 gap-2 mb-4 hover:bg-indigo-500"
                    onClick={toggleAddUnitPopup}
                >
                    <span className="mr-2">Add Unit</span>
                    <IoIosAddCircleOutline className="text-white text-xl" />
                </button>
            </div>

            {
                addUnitPopup && <AddUnitPopup closeModal={toggleAddUnitPopup} userId={parseInt(auth.id ?? '')} buildingId={parseInt(buildingId ?? '')} />
            }

            {
                editUnitPopup && selectedUnit && <EditUnitPopup closeModal={() => setEditUnitPopup(false)} selectedUnit={selectedUnit} userId={parseInt(auth.id ?? '')} buildingId={parseInt(buildingId ?? '')} />
            }

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
                {
                    units.map((card, index) => {
                        return (
                            <div key={index} className="bg-white rounded-md border border-gray-200 p-6 shadow-default shadow-black/5 ">
                                <div className="flex justify-between mb-10">
                                    <div>
                                        <div className="flex items-center mb-1">
                                            <div className="text-2xl font-semibold">{card.name}</div>
                                        </div>
                                        <div className="text-sm font-medium text-gray-400">{card.condoType}</div>
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
                                    <IoSettingsOutline className="mt-1.5 text-xl text-primary cursor-pointer hover:text-red-800" onClick={() => handleSettingsClick(card)} />
                                </div>
                                <Link to={`/manage-unit/${card.id}`} className="text-primary font-medium text-sm hover:text-red-800">View</Link>
                            </div>
                        );
                    })
                }
            </div>

        </DefaultLayout>
    );
};

export default ManageUnits;
