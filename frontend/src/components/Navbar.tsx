import React, { useState } from 'react';
import RentlyLogo from '../assets/icons/rently-logo.svg?react';
import { Link } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import tw from 'tailwind-styled-components';

const Navbar: React.FC = () => {
    const [isOpen, setIsOpen] = useState<boolean>(false);
    const { auth } = useAuth();

    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };

    return (
        <NavbarContainer>
            <NavbarContent>
                <NavbarFlexContainer>
                    <NavbarFlex>
                        <NavbarLogo to="/">
                            <RentlyLogo className="w-auto h-9 sm:h-10" />
                            <NavbarLogoText>Rently</NavbarLogoText>
                        </NavbarLogo>

                        <NavbarMenuButton>
                            <NavbarToggleButton onClick={toggleMenu} type="button" aria-label="toggle menu">
                                {!isOpen ? (
                                    <svg xmlns="http://www.w3.org/2000/svg" className="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
                                        <path strokeLinecap="round" strokeLinejoin="round" d="M4 8h16M4 16h16" />
                                    </svg>
                                ) : (
                                    <svg xmlns="http://www.w3.org/2000/svg" className="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
                                        <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
                                    </svg>
                                )}
                            </NavbarToggleButton>
                        </NavbarMenuButton>
                    </NavbarFlex>


                    <NavbarMenu className={`${isOpen ? 'translate-x-0 opacity-100' : 'opacity-0 -translate-x-full'}`}>
                        <NavbarMenuItems>
                            <NavbarMenuItem href="#">Landing</NavbarMenuItem>
                            <NavbarMenuItem href="#">Account</NavbarMenuItem>
                            <NavbarMenuItem href="#">About</NavbarMenuItem>
                            <NavbarMenuItem href="#">Contact Us</NavbarMenuItem>
                        </NavbarMenuItems>

                        <NavbarProfile>
                            {auth?.access_token ? (
                                <>
                                    <NavbarProfileButton aria-label="show notifications">
                                        <svg className="w-6 h-6" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <path d="M15 17H20L18.5951 15.5951C18.2141 15.2141 18 14.6973 18 14.1585V11C18 8.38757 16.3304 6.16509 14 5.34142V5C14 3.89543 13.1046 3 12 3C10.8954 3 10 3.89543 10 5V5.34142C7.66962 6.16509 6 8.38757 6 11V14.1585C6 14.6973 5.78595 15.2141 5.40493 15.5951L4 17H9M15 17V18C15 19.6569 13.6569 21 12 21C10.3431 21 9 19.6569 9 18V17M15 17H9" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" />
                                        </svg>
                                    </NavbarProfileButton>

                                    <button type="button" className="flex items-center focus:outline-none" aria-label="toggle profile dropdown">
                                        <NavbarProfileDropdown>
                                            <NavbarProfileImage src="https://images.unsplash.com/photo-1517841905240-472988babdf9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80" className="object-cover w-full h-full" alt="avatar" />
                                        </NavbarProfileDropdown>

                                        <NavbarProfileName>User Name</NavbarProfileName>
                                    </button>
                                </>
                            ) : (
                                <>
                                    <button type="button" className="flex items-center focus:outline-none">
                                        <NavbarMenuLink to="/login">Login</NavbarMenuLink>
                                    </button>
                                </>
                            )}
                        </NavbarProfile>
                    </NavbarMenu>
                </NavbarFlexContainer>
            </NavbarContent>
        </NavbarContainer>
    );
};

export default Navbar;

const NavbarContainer = tw.nav`
    relative bg-white shadow
`;

const NavbarContent = tw.div`
    container px-6 py-4 mx-auto
`;

const NavbarFlexContainer = tw.div`
lg:flex lg:items-center lg:justify-between
`;

const NavbarFlex = tw.div`
    flex items-center justify-between
`;

const NavbarLogo = tw(Link)`
    flex flex-row justify-items-center content-center items-center
`;

const NavbarLogoText = tw.div`
    justify-self-center align-self-center text-2xl font-bold
`;

const NavbarMenuButton = tw.div`
    flex lg:hidden
`;

const NavbarToggleButton = tw.button`
    text-gray-500 hover:text-gray-600 focus:outline-none focus:text-gray-600 
`;

const NavbarMenu = tw.div`
    absolute inset-x-0 z-20 w-full px-6 py-4 transition-all duration-300 ease-in-out bg-white lg:mt-0 lg:p-0 lg:top-0 lg:relative lg:bg-transparent lg:w-auto lg:opacity-100 lg:translate-x-0 lg:flex lg:items-center
`;

const NavbarMenuItems = tw.div`
    flex flex-col -mx-6 lg:flex-row lg:items-center lg:mx-8
`;

const NavbarMenuItem = tw.a`
    px-3 py-2 mx-3 mt-2 text-gray-700 transition-colors duration-300 transform rounded-md lg:mt-0  hover:bg-gray-100 
`;

const NavbarMenuLink = tw(Link)`
    block px-5 py-2 text-sm font-medium tracking-wider text-center text-white transition-colors duration-300 transform bg-gray-900 rounded-md hover:bg-gray-700  
`;

const NavbarProfile = tw.div`
    flex items-center mt-4 lg:mt-0
`;

const NavbarProfileButton = tw.button`
    hidden mx-4 text-gray-600 transition-colors duration-300 transform lg:block  hover:text-gray-700  focus:text-gray-700  focus:outline-none
`;

const NavbarProfileDropdown = tw.div`
    w-8 h-8 overflow-hidden border-2 border-gray-400 rounded-full
`;

const NavbarProfileImage = tw.img`
    object-cover w-full h-full
`;

const NavbarProfileName = tw.h3`
    mx-2 text-gray-700  lg:hidden
`;
