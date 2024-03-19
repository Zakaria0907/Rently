import React, { ChangeEvent, FormEvent, useState } from 'react';
import {
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalFooter,
    ModalBody,
    ModalCloseButton,
    Button,
    FormControl,
    FormLabel,
    Input,
} from '@chakra-ui/react';
import Fileupload from '../components/FileUploadBtn';

interface OwnerData {
    name: string;
    lastName: string;
    picture: string;
  }
  
  // Define a type for the component props
  interface OwnerPopupProps {
    editMode: boolean;
    ownerData?: OwnerData;
    closeModal: () => void;
    saveOwner: (owner: OwnerData) => void;
  }

const OwnerPopup: React.FC<OwnerPopupProps> = ({ editMode, ownerData, closeModal, saveOwner }) => {
    const initialFormState = { name: '', lastName: '', picture: '' };
    const [formData, setFormData] = useState(ownerData || initialFormState);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        // Here you would usually send the data to the backend
        // But for now, we can just log it to the console
        console.log(formData);
        saveOwner(formData);
        closeModal();
    };

    return (
        <Modal isOpen={true} onClose={closeModal}>
            <ModalOverlay />
            <ModalContent>
                <ModalHeader>{editMode ? 'Edit Owner' : 'Add New Owner'}</ModalHeader>
                <ModalCloseButton />
                <form onSubmit={handleSubmit}>
                    <ModalBody>
                        <FormControl id="name" isRequired>
                            <FormLabel>Name</FormLabel>
                            <Input name="name" value={formData.name} onChange={handleChange} placeholder="Name" />
                        </FormControl>
                        <FormControl id="lastName" mt={4} isRequired>
                            <FormLabel>Last Name</FormLabel>
                            <Input name="lastName" value={formData.lastName} onChange={handleChange} placeholder="Last Name" />
                        </FormControl>
                        
                        <FormControl id="picture" mt={4}>
                            <FormLabel>Picture Upload </FormLabel>
                            <Fileupload/>
                        </FormControl>
                    </ModalBody>
                    <ModalFooter>
                        <Button colorScheme="blue" mr={3} onClick={closeModal}>
                            Close
                        </Button>
                        <Button type="submit" colorScheme="green">{editMode ? 'Save Changes' : 'Add Owner'}</Button>
                    </ModalFooter>
                </form>
            </ModalContent>
        </Modal>
    );
};

export default OwnerPopup;