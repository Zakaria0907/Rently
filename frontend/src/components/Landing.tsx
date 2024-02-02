import tw from "tailwind-styled-components"
import React from 'react';
import { Link } from 'react-router-dom';

const Landing: React.FC = () => {
    return (
        <Container >
            <TitleContainer >Welcome to our Landing Page</TitleContainer>
            <StyledDescription >Start exploring our amazing features!</StyledDescription>
            <StyledButton >
                Get Started
            </StyledButton>
            <Link to="/login" className="px-4 py-2 mt-4 bg-gray-500 text-white rounded hover:bg-gray-600">
                Login
            </Link>
        </Container>
    );
};

export default Landing;

const Container = tw.div`flex flex-col items-center justify-center h-screen bg-gray-100`

const TitleContainer = tw.h1`text-4xl font-bold mb-4`

const StyledDescription = tw.p`text-lg text-gray-600 mb-8`

const StyledButton = tw.button`px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600`