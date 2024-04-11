import React, { useEffect,useState } from 'react'
import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import DefaultLayout from '../DefaultLayout';
import useAxiosPrivate from '../../hooks/useAxiosPrivate';
import { useParams } from 'react-router-dom';
import { Unit } from '../../types/types';
import GenerateKey from './GenerateKey';
import toast, { Toaster } from 'react-hot-toast';



const ViewUnit: React.FC = () => {
    const axiosPrivate = useAxiosPrivate();
    const { id } = useParams<{ id: string }>();
    const [unit, setUnit] = useState<Unit | null>(null); // State to store the unit object
    const [isGenerateKeyOpen, setIsGenerateKeyOpen] = useState(false);
   

    const fetchUnits = async () => {
        try {
            const response = await axiosPrivate.get(`/company-admin/condos/all`);
            console.log("Units fetched: ", response.data);
            const foundUnit = response.data.find((unit: Unit) => unit.id === Number(id)); // Find the unit with the specified unitId
            setUnit(foundUnit || null); // Set the found unit in state, or null if not found
        } catch (error) {
            console.error(error);
        }
    };

    const handleGenerateKeyClick = () => {
        setIsGenerateKeyOpen(true);
      };
    
      const handleGenerateKeyClose =  () => {
        setIsGenerateKeyOpen(false);
      };
    
      const handleKeyGeneratedSuccess = async () => {
        setIsGenerateKeyOpen(false);
        
    try {
        await fetchUnits(); 
        unit && toast.success(`The new key ${unit.registration_key ? unit.registration_key : ''} generated successfully`);

       
    } catch (error) {
        console.error(error);
    }
      };

    useEffect(() => {
        fetchUnits();
    }, []);

    return (
        <DefaultLayout>
        <Toaster position="top-center" />
       <Breadcrumb pageName={`Manage Unit | Unit ${id}`} />
        <div>
            {unit && (
                <>
                    {isGenerateKeyOpen && unit && <GenerateKey unit={unit} onClose={handleGenerateKeyClose} onGenerated={handleKeyGeneratedSuccess} />}
                    <div className="bg-white rounded-md border border-gray-200 p-6 shadow-default shadow-black/5">
                     <div className="grid grid-cols-2 gap-4">
                        <p><strong>Building ID:</strong> {unit.building_id}</p>
                        <p><strong>Unit ID:</strong> {unit.id}</p>
                        <p><strong>Unit Number:</strong> {unit.unit_number}</p>
                        <p><strong>Address:</strong> {unit.address}</p>
                        <p><strong>Status:</strong> {unit.status}</p>
                        <p><strong>Locker ID:</strong> {unit.locker_id ? unit.locker_id : "Not Assigned"}</p>
                        <p><strong>Parking ID:</strong> {unit.parking_id ? unit.parking_id : "Not Assigned"}</p>
                        <p><strong>Registration Key:</strong> {unit.registration_key ? unit.registration_key : "Not Assigned"}</p>
                    </div>
                    {unit.registration_key === null && (
                                <div className="flex justify-end">
                                    <button onClick={handleGenerateKeyClick} className="mt-4 bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:ring focus:ring-blue-300">
                                        Generate Key
                                    </button>
                                </div>
                            )}
                    </div>
                   
                    
                    
                </>
            )}
            
        </div>
        </DefaultLayout>
    );
};
  
  export default ViewUnit;