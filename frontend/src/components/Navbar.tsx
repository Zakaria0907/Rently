import tw from "tailwind-styled-components";
import React from 'react';

const Navbar: React.FC = () => {
    return (
        <Header>
            <MainContainer aria-label="Global">
                <LogoContainer>
                    <Logo href="#" aria-label="Brand">Brand</Logo>
                    <RightNavBarContainer>
                        <NavbarToggleButton />
                    </RightNavBarContainer>
                </LogoContainer>
                <NavbarCollapse>
                    <NavbarItemsContainer>
                        <NavbarLink href="#" aria-current="page">Landing</NavbarLink>
                        <NavbarLink href="#">Account</NavbarLink>
                        <NavbarLink href="#">Work</NavbarLink>
                        <NavbarLink href="#">Blog</NavbarLink>

                        <DropdownButtonContainer>
                            <DropdownToggleButton />
                            <DropdownMenu>
                                <DropdownLink href="#">About</DropdownLink>
                                <SubDropdownButtonContainer>
                                    <SubDropdownToggleButton />
                                    <SubDropdownMenu>
                                        <DropdownLink href="#">About</DropdownLink>
                                        <DropdownLink href="#">Downloads</DropdownLink>
                                        <DropdownLink href="#">Team Account</DropdownLink>
                                    </SubDropdownMenu>
                                </SubDropdownButtonContainer>
                                <DropdownLink href="#">Downloads</DropdownLink>
                                <DropdownLink href="#">Team Account</DropdownLink>
                            </DropdownMenu>
                        </DropdownButtonContainer>

                        <LoginLink href="#">
                            <StyledSVG xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z" />
                            </StyledSVG>
                            Log in
                        </LoginLink>
                    </NavbarItemsContainer>
                </NavbarCollapse>
            </MainContainer>
        </Header>
    );
};

export default Navbar;

// Styled Components
const Header = tw.header`flex flex-wrap sm:justify-start sm:flex-nowrap z-50 w-full bg-white border-b border-gray-200 text-sm py-3 sm:py-0 dark:bg-gray-800 dark:border-gray-700`;

const MainContainer = tw.nav`relative max-w-7xl w-full mx-auto px-4 sm:flex sm:items-center sm:justify-between sm:px-6 lg:px-8`;

const LogoContainer = tw.div`flex items-center justify-between`;

const Logo = tw.a`flex-none text-xl font-semibold dark:text-white`;

const RightNavBarContainer = tw.div`sm:hidden`;


const NavbarToggleButton = tw.button`
  hs-collapse-toggle w-9 h-9 flex justify-center items-center text-sm font-semibold rounded-lg border border-gray-200 text-gray-800 hover:bg-gray-100 disabled:opacity-50 disabled:pointer-events-none dark:text-white dark:border-gray-700 dark:hover:bg-gray-700 dark:focus:outline-none dark:focus:ring-1 dark:focus:ring-gray-600
`;

const NavbarCollapse = tw.div`
  hs-collapse hidden overflow-hidden transition-all duration-300 basis-full grow sm:block
`;

const NavbarItemsContainer = tw.div`
  flex flex-col gap-y-4 gap-x-0 mt-5 sm:flex-row sm:items-center sm:justify-end sm:gap-y-0 sm:gap-x-7 sm:mt-0 sm:ps-7
`;

const NavbarLink = tw.a`
  font-medium text-gray-500 hover:text-gray-400 sm:py-6 dark:text-gray-400 dark:hover:text-gray-500
`;

const DropdownButtonContainer = tw.div`
  hs-dropdown [--strategy:static] sm:[--strategy:fixed] [--adaptive:none] sm:[--trigger:hover] sm:py-4
`;

const DropdownToggleButton = tw.button`
  flex items-center w-full text-gray-500 hover:text-gray-400 font-medium dark:text-gray-400 dark:hover:text-gray-500
`;

const DropdownMenu = tw.div`
  hs-dropdown-menu transition-[opacity,margin] duration-[0.1ms] sm:duration-[150ms] hs-dropdown-open:opacity-100 opacity-0 sm:w-48 hidden z-10 bg-white sm:shadow-md rounded-lg p-2 dark:bg-gray-800 sm:dark:border dark:border-gray-700 dark:divide-gray-700 before:absolute top-full sm:border before:-top-5 before:start-0 before:w-full before:h-5
`;

const DropdownLink = tw.a`
  flex items-center gap-x-3.5 py-2 px-3 rounded-lg text-sm text-gray-800 hover:bg-gray-100 focus:ring-2 focus:ring-blue-500 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-gray-300 dark:focus:outline-none dark:focus:ring-1 dark:focus:ring-gray-600
`;

const SubDropdownButtonContainer = tw.div`
  hs-dropdown relative [--strategy:static] sm:[--strategy:absolute] [--adaptive:none] sm:[--trigger:hover]
`;

const SubDropdownToggleButton = tw.button`
  w-full flex justify-between items-center text-sm text-gray-800 rounded-lg py-2 px-3 hover:bg-gray-100 focus:ring-2 focus:ring-blue-500 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-gray-300 dark:focus:outline-none dark:focus:ring-1 dark:focus:ring-gray-600
`;

const SubDropdownMenu = tw.div`
  hs-dropdown-menu transition-[opacity,margin] duration-[0.1ms] sm:duration-[150ms] hs-dropdown-open:opacity-100 opacity-0 sm:w-48 hidden z-10 sm:mt-2 bg-white sm:shadow-md rounded-lg p-2 dark:bg-gray-800 sm:dark:border dark:border-gray-700 dark:divide-gray-700 before:absolute sm:border before:-end-5 before:top-0 before:h-full before:w-5 top-0 end-full !mx-[10px]
`;

const LoginLink = tw.a`
  flex items-center gap-x-2 font-medium text-gray-500 hover:text-blue-600 sm:border-s sm:border-gray-300 sm:my-6 sm:ps-6 dark:border-gray-700 dark:text-gray-400 dark:hover:text-blue-500
`;



const StyledSVG = tw.svg`flex-shrink-0 w-4 h-4`