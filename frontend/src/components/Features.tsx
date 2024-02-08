import React from 'react';
import CondoImage from "../assets/images/condo-image-hero.jpg";


const Features: React.FC = () => {
    return (
        <div className="max-w-[85rem] px-4 py-10 sm:px-6 lg:px-8 lg:py-14 mx-auto">
            <div className="lg:grid lg:grid-cols-12 lg:gap-16 lg:items-center">
                <div className="lg:col-span-7">
                    <div className="grid grid-cols-12 gap-2 sm:gap-6 items-center lg:-translate-x-10">
                        <div className="col-span-4">
                            <img className="rounded-xl" src="https://images.unsplash.com/photo-1606868306217-dbf5046868d2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1981&q=80" alt="Image Description" />
                        </div>
                        <div className="col-span-3">
                            <img className="rounded-xl" src="https://images.unsplash.com/photo-1605629921711-2f6b00c6bbf4?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80" alt="Image Description" />
                        </div>
                        <div className="col-span-5">
                            <img className="rounded-xl" src="https://images.unsplash.com/photo-1600194992440-50b26e0a0309?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80" alt="Image Description" />
                        </div>
                    </div>
                </div>
                <div className="mt-5 sm:mt-10 lg:mt-0 lg:col-span-5">
                    <div className="space-y-6 sm:space-y-8">
                        <div className="space-y-2 md:space-y-4">
                            <h2 className="font-bold text-3xl lg:text-4xl text-gray-800">
                                Empowering Property Management with Smart Tools
                            </h2>
                            <p className="text-gray-500">
                                Leverage our suite of tools to manage your properties more effectively and share updates effortlessly with residents.
                            </p>
                        </div>
                        <ul role="list" className="space-y-2 sm:space-y-4">
                            <li className="flex space-x-3">
                                <span className="mt-0.5 h-5 w-5 flex justify-center items-center rounded-full bg-blue-50 text-blue-600">
                                    <svg className="flex-shrink-0 h-3.5 w-3.5" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                                        <polyline points="20 6 9 17 4 12" />
                                    </svg>
                                </span>
                                <span className="text-sm sm:text-base text-gray-500">
                                    <span className="font-bold">Reduce operational overhead</span> – more creativity
                                </span>
                            </li>
                            <li className="flex space-x-3">
                                <span className="mt-0.5 h-5 w-5 flex justify-center items-center rounded-full bg-blue-50 text-blue-600">
                                    <svg className="flex-shrink-0 h-3.5 w-3.5" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                                        <polyline points="20 6 9 17 4 12" />
                                    </svg>
                                </span>
                                <span className="text-sm sm:text-base text-gray-500">
                                    Streamline communication and save time
                                </span>
                            </li>
                            <li className="flex space-x-3">
                                <span className="mt-0.5 h-5 w-5 flex justify-center items-center rounded-full bg-blue-50 text-blue-600">
                                    <svg className="flex-shrink-0 h-3.5 w-3.5" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                                        <polyline points="20 6 9 17 4 12" />
                                    </svg>
                                </span>
                                <span className="text-sm sm:text-base text-gray-500">
                                     Optimize expenses and <span className="font-bold">manage budgets effectively</span>
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Features;