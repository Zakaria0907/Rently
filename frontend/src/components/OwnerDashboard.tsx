import React from 'react';
import Card from './SharedComponents/Card';
import CondoImg from '../assets/images/condo-image-hero.jpg'; // Add image import
import { Avatar, AvatarBadge, Stack } from '@chakra-ui/react';
import CustomModal from './SharedComponents/CustomModal';

const OwnerDashboard: React.FC = () => {
    const [isOpen, setIsOpen] = React.useState(false);
    const [selectedProperty, setSelectedProperty] = React.useState({} as any); // Add selectedProperty state
    const [cards, setCards] = React.useState([{
        title: "",
        description: "",
        image: CondoImg
    }]);

    React.useEffect(() => {
        // Generate card properties
        const generatedCards = [
            { title: "Property 1", description: "Property Details", image: CondoImg },
            { title: "Property 2", description: "Property Details", image: CondoImg },
            { title: "Property 3", description: "Property Details", image: CondoImg }
        ];

        // Set cards on mount
        setCards(generatedCards);
    }, []);

    const toggleModal = (card : any) => {
        setSelectedProperty(card);
        setIsOpen(!isOpen);
    }

    const closeModal = () => {
        setIsOpen(false);
    };
    
    return (
        <>
            <div className='px-4 py-10 sm:px-6 lg:px-8 lg:py-14 mx-auto'>
                <div className='flex items-center space-x-4 mb-4'>
                    <Stack direction='row' spacing={4}>
                        <Avatar>
                            <AvatarBadge boxSize='1.25em' bg='green.500' />
                        </Avatar>
                    </Stack>
                    <h1 className='text-3xl font-semibold text-gray-800'>Owner Dashboard</h1>
                </div>
                <div className="max-w-[85rem] py-10  lg:py-14 mx-auto">
                    <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-6">
                        {
                            cards.map((card, index) => (
                                <Card
                                    key={index}
                                    title={card.title}
                                    description={card.description}
                                    image={card.image}
                                    onDetailsClick={() => toggleModal(card)}
                                />
                            ))
                        }
                    </div>
                </div>
            </div>
            { isOpen && <CustomModal isOpen={isOpen} onClose={closeModal} title={selectedProperty.title}>
                <div>
                    <p className="text-gray-600">Address: 123 Main St</p>
                    <p className="text-gray-600">Bedrooms: 3</p>
                    <p className="text-gray-600">Bathrooms: 2</p>
                </div>
            </CustomModal> }
        </>
    );
};

export default OwnerDashboard;