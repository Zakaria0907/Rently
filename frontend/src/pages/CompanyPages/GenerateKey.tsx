import React, {useState} from 'react'
import { Button, Modal, ModalOverlay, ModalContent, ModalHeader, ModalCloseButton, ModalBody, FormControl, FormLabel, Input, ModalFooter } from "@chakra-ui/react";
import useAxiosPrivate from '../../hooks/useAxiosPrivate';
import { Unit } from '../../types/types';



interface GenerateKeyProps {
    unit: Unit;
    onClose: () => void;
    onGenerated: () => void;
  }


const GenerateKey: React.FC<GenerateKeyProps> = ({ unit,onClose, onGenerated }) => {
  
    const axiosPrivate = useAxiosPrivate();
    const [occupantType, setOccupantType] = useState('');
    const [monthlyRent, setMonthlyRent] = useState('');

   

    const generateKey = async () => {
        try {
            const response = await axiosPrivate.post(`/company-admin/generate-key-and-create-housing-contract-for-condo`,{
                "registrationKeyRequestDto": {
                    "building_id": unit.building_id,
                    "condo_id": unit.id,
                    "role": occupantType.toUpperCase()
                  },
                  "housingContractDto": {
                    "occupant_type": occupantType.toUpperCase(),
                    "monthly_rent": Number(monthlyRent)
                  }
            });
            console.log("Generated Key: ", response.data);
             
             onGenerated();
         
          
        } catch (error) {
            console.error(error);
            onClose();
            
        }
    };
  
    return (
        <Modal isOpen onClose={onClose}>
            <ModalOverlay />
            <ModalContent>
                <ModalHeader>Create your account</ModalHeader>
                <ModalCloseButton />
                <ModalBody pb={6}>
                    <FormControl>
                        <FormLabel>Occupant Type</FormLabel>
                        <Input
                            type="text"
                            id="occupant-type"
                            value={occupantType}
                            onChange={(e) => setOccupantType(e.target.value.toUpperCase())}
                            placeholder='Occupant Type'
                        />
                    </FormControl>

                    <FormControl mt={4}>
                        <FormLabel>Monthly Rent</FormLabel>
                        <Input
                            type="number"
                            id="monthly-rent"
                            value={monthlyRent}
                            onChange={(e) => setMonthlyRent(e.target.value)}
                            placeholder='Monthly Rent'
                        />
                    </FormControl>
                </ModalBody>

                <ModalFooter>
                    <Button colorScheme='blue' mr={3} onClick={generateKey}>
                        Generate Key
                    </Button>
                    <Button onClick={onClose}>Cancel</Button>
                </ModalFooter>
            </ModalContent>
        </Modal>
    );
  }
  
  export default GenerateKey;
