import tw from "tailwind-styled-components"
import React from 'react';

const MyComponent: React.FC = () => {
    return (
        <ExampleContainer>
            <div className="mt-20 grid gap-6 grid-cols-2 sm:gap-12 lg:grid-cols-3 lg:gap-8">
                <div>
                    <Heading>Accuracy rate</Heading>
                    <Subheading>99.95%</Subheading>
                    <Text>in fulfilling orders</Text>
                </div>
                <div>
                    <Heading>Startup businesses</Heading>
                    <Subheading>2,000+</Subheading>
                    <Text>partner with Preline</Text>
                </div>
                <div>
                    <Heading>Happy customer</Heading>
                    <Subheading>85%</Subheading>
                    <Text>this year alone</Text>
                </div>
            </div>
        </ExampleContainer>
    );
};

export default MyComponent;

const ExampleContainer = tw.div`max-w-[85rem] px-4 py-10 sm:px-6 lg:px-8 lg:py-14 mx-auto`
const Heading = tw.h4`text-lg sm:text-xl font-semibold text-gray-800 dark:text-gray-200`
const Subheading = tw.p`mt-2 sm:mt-3 text-4xl sm:text-6xl font-bold text-blue-600`
const Text = tw.p`mt-1 text-gray-500`