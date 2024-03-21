import React, { useState } from 'react';
import tw from 'tailwind-styled-components';
import ReceiptIcon from '../images/icon/receipt.svg?react';
import CustomModal from './SharedComponents/CustomModal';
import { useDisclosure } from '@chakra-ui/react'
import AddPropertyPopup from './AddPropertyPopup';


const PropertyTable: React.FC = () => {
    const [properties, setProperties] = useState<any[]>([]);
    const { isOpen, onOpen, onClose } = useDisclosure();
    const [addPropertyPopup, setaddPropertyPopup] = useState<Boolean>(false);
    const [selectedProperty, setSelectedProperty] = useState<any>({
        id: 0,
        name: "",
        address: "",
    });

    const togglePropertyPopup = () => {
        setaddPropertyPopup(!addPropertyPopup);
    };

    // const addProperty = (property: any) => {
    //     setProperties([...properties, property]);
    //     togglePropertyPopup();
    //     console.log(property);
    // };

    const openServicesModal = (property: any) => {
        setSelectedProperty(property);
        onOpen();
    };

    React.useEffect(() => {
        // Fetch properties from API
        // setProperties(response.data);

        // For testing purposes
        const tempProperties = [
            { id: 1, name: "Property 1", address: "123 Main St" },
            { id: 2, name: "Property 2", address: "456 Elm St" },
            { id: 3, name: "Property 3", address: "789 Oak St" },
        ];
        setProperties(tempProperties);
    }, []);

    return (
        <>
            <Container>
                <FlexContainer>
                    <OverflowContainer>
                        <InnerContainer>
                            <CardContainer>
                                <CardHeader>
                                    <LeftSideContainer>
                                        <CardTitle>
                                            Properties
                                        </CardTitle>
                                        <CardSubtitle>
                                            Add property, edit, access services and more.
                                        </CardSubtitle>
                                    </LeftSideContainer>
                                    <Button onClick={togglePropertyPopup} >+</Button>
                                    {addPropertyPopup && <AddPropertyPopup closeModal={togglePropertyPopup} company_id={1} />}
                                </CardHeader>
                                {
                                    properties.length > 0 ? (
                                        <CardContent>
                                            <table className="min-w-full">
                                                <thead>
                                                    <tr>
                                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
                                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Address</th>
                                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody className="bg-white divide-y divide-gray-200">
                                                    {
                                                        properties.map((property) => (
                                                            <tr key={property.id}>
                                                                <td className="px-6 py-4 whitespace-nowrap">
                                                                    <div className="text-sm font-medium text-gray-900">{property.name}</div>
                                                                </td>
                                                                <td className="px-6 py-4 whitespace-nowrap">
                                                                    <div className="text-sm text-gray-900">{property.address}</div>
                                                                </td>
                                                                <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                                                    <button onClick={() => openServicesModal(property)} className="text-indigo-600 hover:text-indigo-900">Services</button>
                                                                </td>
                                                            </tr>
                                                        ))
                                                    }
                                                </tbody>
                                            </table>
                                        </CardContent>
                                    ) : (
                                        <EmptyCardContent>
                                            <IconContainer>
                                                <Icon xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                                    <ReceiptIcon />
                                                </Icon>
                                            </IconContainer>

                                            <Title>
                                                No properties found
                                            </Title>
                                            <Subtitle>
                                                Link your property to start managing your services and invoices.
                                            </Subtitle>
                                            <div>
                                                <LinkContainer>
                                                    Learn more
                                                    <LinkIcon xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="m9 18 6-6-6-6" /></LinkIcon>
                                                </LinkContainer>
                                            </div>

                                            <ButtonContainer>
                                                <PrimaryButton>
                                                    <Icon xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M5 12h14" /><path d="M12 5v14" /></Icon>
                                                    Create a new invoice
                                                </PrimaryButton>
                                                <SecondaryButton>
                                                    Use a Template
                                                </SecondaryButton>
                                            </ButtonContainer>
                                        </EmptyCardContent>
                                    )
                                }
                                <FooterContainer>
                                    <div>
                                        <ResultText>
                                            <span className="font-semibold text-gray-800">0</span> results
                                        </ResultText>
                                    </div>

                                    <div>
                                        <NavigationContainer>
                                            <PrevButton disabled>
                                                <Icon xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="m15 18-6-6 6-6" /></Icon>
                                                Prev
                                            </PrevButton>

                                            <NextButton disabled>
                                                Next
                                                <Icon xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="m9 18 6-6-6-6" /></Icon>
                                            </NextButton>
                                        </NavigationContainer>
                                    </div>
                                </FooterContainer>
                            </CardContainer>
                        </InnerContainer>
                    </OverflowContainer>
                </FlexContainer>
                <CustomModal isOpen={isOpen} onClose={onClose} title={`Services for ${selectedProperty.name || ""}`}>
                    <div className="text-sm text-gray-900">{selectedProperty.address}</div>
                </CustomModal>
            </Container>
        </>
    );
};

export default PropertyTable;

const Container = tw.div`
        max-w-[85rem] px-4 py-10 sm:px-6 lg:px-8 lg:py-14 mx-auto
    `;
const FlexContainer = tw.div`
        flex flex-col
    `;
const OverflowContainer = tw.div`
        -m-1.5 overflow-x-auto
    `;
const InnerContainer = tw.div`
        p-1.5 min-w-full inline-block align-middle
    `;
const CardContainer = tw.div`
        bg-white border border-gray-200 rounded-xl shadow-sm overflow-hidden
    `;
const CardHeader = tw.div`
        flex flex-direction-col justify-between items-center px-6 py-4 border-b border-gray-200 sm:flex-row
    `;
const CardTitle = tw.h2`
        text-xl font-semibold text-gray-800 
    `;
const CardSubtitle = tw.p`
        text-sm text-gray-600 
    `;
const CardContent = tw.div`
     w-full min-h-[400px] flex flex-col 
    `;
const EmptyCardContent = tw.div`
        max-w-sm w-full min-h-[400px] flex flex-col justify-center mx-auto px-6 py-4
    `;
const IconContainer = tw.div`
        flex justify-center items-center w-[46px] h-[46px] bg-gray-100 rounded-lg 
    `;
const Icon = tw.svg`
        flex-shrink-0 w-6 h-6 text-gray-600
    `;
const Title = tw.h2`
        mt-5 font-semibold text-gray-800
    `;
const Subtitle = tw.p`
        mt-2 text-sm text-gray-600
    `;
const LinkContainer = tw.div`
        inline-flex items-center gap-x-1 text-sm text-blue-600 decoration-2 hover:underline font-medium
    `;
const LinkIcon = tw.svg`
        flex-shrink-0 w-4 h-4
    `;
const ButtonContainer = tw.div`
        mt-5 grid sm:flex gap-2
    `;
const PrimaryButton = tw.button`
        py-2 px-3 inline-flex justify-center items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-blue-600 text-white hover:bg-blue-700 disabled:opacity-50 disabled:pointer-events-none
    `;
const SecondaryButton = tw.button`
        py-2 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg border border-gray-200 bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none
    `;
const FooterContainer = tw.div`
        px-6 py-4 grid gap-3 md:flex md:justify-between md:items-center border-t border-gray-200
    `;
const ResultText = tw.p`
        text-sm text-gray-600
    `;
const NavigationContainer = tw.div`
        inline-flex gap-x-2
    `;
const PrevButton = tw.button`
        py-2 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg border border-gray-200 bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none
    `;
const NextButton = tw.button`
        py-2 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg border border-gray-200 bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none
    `;
const LeftSideContainer = tw.div`
    
`;

const Button = tw.button`
       px-5 py-1.5 text-sm font-medium tracking-wide text-white text-lg capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50
`;
