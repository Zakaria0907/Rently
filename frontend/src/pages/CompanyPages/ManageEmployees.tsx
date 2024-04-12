import React, { useEffect, useState } from 'react';
import DefaultLayout from '../DefaultLayout';
import { Link, useNavigate } from 'react-router-dom';
import { IoIosCreate, IoIosTrash, IoMdAddCircleOutline } from "react-icons/io";
import { Avatar, Image } from '@chakra-ui/react';
import OwnerPopup from '../../components/OwnerPopup'; // Popup for adding/editing an owner
// import useAxiosPrivate from '../../hooks/useAxiosPrivate';
import UserOne from '../../images/user/user-01.png';
import UserTwo from '../../images/user/user-02.png';
import UserThree from '../../images/user/user-03.png';
import UserFour from '../../images/user/user-04.png';
import useAxiosPrivate from '../../hooks/useAxiosPrivate';
import { Employee } from '../../types/types';
import toast from 'react-hot-toast';

interface OwnerData {
    id?: number; // Make id optional for adding new owners where id might not be present
    name: string;
    lastName: string;
    picture: string;
  }

//   interface OwnerPopupProps {
//     editMode: boolean;
//     ownerData?: OwnerData | null; // Allow null
//     closeModal: () => void;
//     saveOwner: (owner: OwnerData) => void;
//   }

const initialOwners = [
    {
      id: 1,
      name: 'John',
      lastName: 'Doe',
      picture: UserOne
    },
    {
      id: 2,
      name: 'Jane',
      lastName: 'Doe',
      picture: UserTwo,
    },
    {
      id: 3,
      name: 'Mike',
      lastName: 'Smith',
      picture: UserThree,
    },
    {
        id: 4,
        name: 'Von',
        lastName: 'Draco',
        picture: UserFour,
      },
  ];

const ManageEmployees: React.FC = () => {
    const [owners, setOwners] = React.useState<any[]>(initialOwners);
    const [ownerPopup, setOwnerPopup] = React.useState({ isOpen: false, editMode: false, ownerData: undefined });
    
    const [employeeID, setEmployeeID] = useState<number>();
    const [allEmployees, setAllEmployees] = useState<User[]>([]); // Initialized as an empty array
    const navigate = useNavigate();

    const axiosPrivate = useAxiosPrivate();
    
    useEffect(() => {
        try {
            fetchEmployees();
        } catch (error) {
            console.log(error);
            toast.error("Network Error");
        }
    }, []);

    async function fetchEmployees(): Promise<void> {
        try {
            const response = await axiosPrivate.get('/company-admin/employees');
            setAllEmployees(response.data);
            console.log("RIGHT HERE");
            console.log(response.data)
        } catch (error) {
            console.error(error);
            toast.error("Network Error");
        }
    }

    const toggleOwnerPopup = (editMode = false, ownerData = undefined) => {
        setOwnerPopup({ isOpen: !ownerPopup.isOpen, editMode, ownerData });
    };

    const addOrEditOwner = (ownerData: OwnerData) => {
        if (ownerPopup.editMode) {
            // Edit mode
            setOwners(owners.map(owner => owner.id === ownerData.id ? { ...ownerData } : owner));
        } else {
            // Add mode
            const newOwner = { ...ownerData, id: Math.max(...owners.map(o => o.id)) + 1 };
            setOwners([...owners, newOwner]);
        }
        toggleOwnerPopup(); // Close the popup after adding/editing
    };

    const deleteOwner = (ownerId: number) => {
        const isConfirmed = window.confirm("Are you sure you want to delete owner #" + ownerId + " ?");
        if (isConfirmed) 
            setOwners(currentOwners => currentOwners.filter(owner => owner.id !== ownerId));
    };
    console.log("HEY")
    console.log(allEmployees);
    return (
        <DefaultLayout>
            <div className="flex justify-between items-center mb-4">
                <h1 className="text-xl font-semibold">Manage Employees</h1>
                <button
                    onClick={() => toggleOwnerPopup()}
                    className="flex items-center bg-primary text-white rounded-md px-3 py-2 gap-2 hover:bg-indigo-500">
                    <span className="mr-2">Add Employee</span>
                    <IoMdAddCircleOutline className="text-xl" />
                </button>
            </div>

            {
                ownerPopup.isOpen &&
                <OwnerPopup
                    editMode={ownerPopup.editMode}
                    ownerData={ownerPopup.ownerData}
                    closeModal={() => toggleOwnerPopup()}
                    saveOwner={addOrEditOwner}
                />
            }

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {owners.map((owner) => (
                    <div key={owner.id} className="bg-white rounded-md border border-gray-200 p-6 shadow-md">
                        <Image
                            src={owner.picture}
                            alt={`${owner.name} ${owner.lastName}`}
                            borderRadius='full'
                            boxSize='100px'
                            objectFit='cover'
                            className='mb-4 mx-auto'
                        />
                        <h3 className="text-lg font-semibold text-center">{owner.name} {owner.lastName}</h3>
                        <div className="flex justify-around mt-4">
                            <Link to={`/admin-owner-details/${owner.id}`} className="text-primary hover:underline">View Details</Link>
                            <IoIosCreate className="text-primary cursor-pointer" onClick={() => toggleOwnerPopup(true, owner)} />
                            <IoIosTrash className="text-red-500 cursor-pointer" onClick={() => deleteOwner(owner.id)} />
                        </div>
                    </div>
                ))}
                {allEmployees.map((employee: Employee) => (
                    <div key={employee.id} className="bg-white rounded-md border border-gray-200 p-6 shadow-md">
                        <div className="flex justify-center mb-4">
                            <Avatar size={'lg'} />
                        </div>
                        <h3 className="text-lg font-semibold text-center mb-4">{employee.first_name} {employee.last_name}</h3>
                        <h4 className="text-md text-center">{employee.email}</h4>
                        <div className="flex justify-around mt-4">
                            <Link to={`/employee-detail/${employee.id}`} state={{ employee }}>
                                View Details
                            </Link>
                            <IoIosCreate className="text-primary cursor-pointer" onClick={() => toggleOwnerPopup(true, employee)} />
                            <IoIosTrash className="text-red-500 cursor-pointer" onClick={() => deleteOwner(employee.id)} />
                        </div>
                    </div>
                ))}
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                
            </div>
        </DefaultLayout>
    );
};

export default ManageEmployees;