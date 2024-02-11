import React from 'react';
import CondoImage from "../images/cover/condo-image-hero.jpg";
import { Link } from 'react-router-dom';
import { FaGoogle, FaAmazon, FaAirbnb  } from "react-icons/fa";
import { FaMeta } from "react-icons/fa6";

const Hero: React.FC = () => {
    return (

        <div className="max-w-[85rem] mx-auto px-4 sm:px-6 lg:px-8 my-4">
            <div className="grid lg:grid-cols-7 lg:gap-x-8 xl:gap-x-12 lg:items-center">
                <div className="lg:col-span-3">
                    <h1 className="block text-3xl font-bold text-gray-800 sm:text-4xl md:text-5xl lg:text-6xl">Manage Your Properties</h1>
                    <p className="mt-3 text-lg text-gray-800">Introducing a new way for you to easily manage all of your properties.</p>

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
                        <span className="text-xs font-medium text-gray-800 uppercase">Trusted by:</span>

                        <div className="mt-4 flex gap-x-8">
                            <FaGoogle className="text-6xl text-gray-800" />
                            <FaAmazon className="text-6xl text-gray-800" />
                            <FaMeta className="text-6xl text-gray-800" />
                            <FaAirbnb className="text-6xl text-gray-800" />
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