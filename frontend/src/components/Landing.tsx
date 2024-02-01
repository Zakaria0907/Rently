import React from 'react';
import { Link } from 'react-router-dom';

const Landing: React.FC = () => {
    return (
        <div className="flex flex-col items-center justify-center h-screen bg-gray-100">
            <h1 className="text-4xl font-bold mb-4">Welcome to our Landing Page</h1>
            <p className="text-lg text-gray-600 mb-8">Start exploring our amazing features!</p>
            <button className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
                Get Started
            </button>
            <Link to="/login" className="px-4 py-2 mt-4 bg-gray-500 text-white rounded hover:bg-gray-600">
                Login
            </Link>
        </div>
    );
};

export default Landing;