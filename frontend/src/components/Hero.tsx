"use client";
import { TypewriterEffectSmooth } from "../other/typewriter-effect";
import { Link } from "react-router-dom";
import CondoImage from "../assets/images/condo-image-hero.jpg";

export default function Hero() {
    const words = [
        {
            text: "Manage",
        },
        {
            text: "your",
        },
        {
            text: "properties",
        },
        {
            text: "with",
        },
        {
            text: "Rently.",
            className: "text-blue-500 dark:text-blue-500",
        },
    ];
    return (
        <header className="bg-white dark:bg-gray-900">
            <div className="lg:flex">
                <div className="flex items-center justify-center w-full px-6 py-8 lg:h-[32rem] lg:w-1/2">
                    <div className="max-w-xl">
                        <h2 className="text-3xl font-semibold text-gray-800 dark:text-white lg:text-4xl">
                        Easily Manage your <span className="text-blue-600 dark:text-blue-400">Properties</span>
                        </h2>

                        <p className="mt-4 text-sm text-gray-500 dark:text-gray-400 lg:text-base">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis commodi cum cupiditate ducimus, fugit harum id necessitatibus odio quam quasi, quibusdam rem tempora voluptates.
                        </p>

                        <div className="flex flex-col mt-6 space-y-3 lg:space-y-0 lg:flex-row">
                            <Link
                                to="/login"
                                className="block px-5 py-2 text-sm font-medium tracking-wider text-center text-white transition-colors duration-300 transform bg-gray-900 rounded-md hover:bg-gray-700"
                            >
                                Sign in
                            </Link>
                            <Link
                                to="/register"
                                className="block px-5 py-2 text-sm font-medium tracking-wider text-center text-gray-700 transition-colors duration-300 transform bg-gray-200 rounded-md lg:mx-4 hover:bg-gray-300"
                            >
                                Sign up
                            </Link>
                        </div>
                    </div>
                </div>

                <div className="w-full h-64 lg:w-1/2 lg:h-auto">
                    <div className="w-full h-full bg-cover" style={{ backgroundImage: `url(${CondoImage})` }}>
                        <div className="w-full h-full bg-black opacity-25"></div>
                    </div>
                </div>
            </div>
        </header>
    );
};
