import React from 'react';
import CondoImage from "../assets/images/condo-image-hero.jpg";
import { Link } from 'react-router-dom';
import { FaGoogle } from "react-icons/fa";

const Hero: React.FC = () => {
    return (
        <div className="max-w-[85rem] mx-auto px-4 sm:px-6 lg:px-8 my-4">
            <div className="grid lg:grid-cols-7 lg:gap-x-8 xl:gap-x-12 lg:items-center">
                <div className="lg:col-span-3">
                    <h1 className="block text-3xl font-bold text-gray-800 sm:text-4xl md:text-5xl lg:text-6xl dark:text-white">Manage Your Properties</h1>
                    <p className="mt-3 text-lg text-gray-800 dark:text-gray-400">Introducing a new way for you to easily manage all of your properties.</p>

                    <div className="mt-5 lg:mt-8 flex flex-col items-center gap-2 sm:flex-row sm:gap-3">
                        <Link
                            to="/login"
                            className="block px-5 py-2 text-sm font-medium tracking-wider text-center text-white transition-colors duration-300 transform bg-gray-900 rounded-md hover:bg-gray-700"
                        >
                            Login
                        </Link>
                        <Link
                            to="/register"
                            className="block px-5 py-2 text-sm font-medium tracking-wider text-center text-gray-700 transition-colors duration-300 transform bg-gray-200 rounded-md lg:mx-4 hover:bg-gray-300"
                        >
                            Signup
                        </Link>
                    </div>

                    <div className="mt-6 lg:mt-12">
                        <span className="text-xs font-medium text-gray-800 uppercase dark:text-gray-200">Trusted by:</span>

                        <div className="mt-4 flex gap-x-8">
                            <FaGoogle className="text-6xl text-gray-800 dark:text-gray-200" />
                            <FaGoogle className="text-6xl text-gray-800 dark:text-gray-200" />
                            <FaGoogle className="text-6xl text-gray-800 dark:text-gray-200" />
                        </div>
                    </div>
                </div>

                <div className="lg:col-span-4 mt-10 lg:mt-0">
                    <img className="w-full rounded-xl" src={CondoImage} alt="Condo" />
                </div>
            </div>
        </div>
    );
}

export default Hero;