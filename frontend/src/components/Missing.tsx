import React from 'react';

const Missing: React.FC = () => {
    return (
        <div className="flex flex-col items-center justify-center h-screen">
            <h1 className="text-4xl font-bold mb-4">404 - Page Not Found</h1>
            <p className="text-lg text-gray-500">Oops! The page you are looking for does not exist.</p>
        </div>
    );
};

export default Missing;
