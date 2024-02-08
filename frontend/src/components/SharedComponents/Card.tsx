import React from 'react';
import CondoImg from '../../assets/images/condo-image-hero.jpg'; // Add image import

interface CardProps {
    title: string;
    description: string;
    image: string; // Add image prop
    onDetailsClick?: () => void;
    onEditClick?: () => void;
}

const Card: React.FC<CardProps> = ({ title, description, image, onDetailsClick, onEditClick }) => {
    return (
        <div className="group flex flex-col h-full bg-white border border-gray-200 shadow-sm rounded-xl ">
            <div className="h-52 flex flex-col justify-center items-center bg-gray-200 rounded-t-xl">
                <img src={image ?? CondoImg} alt={title} className="w-full h-full object-cover rounded-t-xl" /> {/* Add image */}
            </div>
            <div className="p-4 md:p-6">
                <span className="block mb-1 text-xs font-semibold uppercase text-blue-600 ">
                    {title}
                </span>
                <h3 className="text-xl font-semibold text-gray-800 ">
                    {title}
                </h3>
                <p className="mt-3 text-gray-500">
                    {description}
                </p>
            </div>
            <div className="mt-auto flex border-t border-gray-200 divide-x divide-gray-200 ">
                <button onClick={onDetailsClick} className="w-full py-3 px-4 inline-flex justify-center items-center gap-x-2 text-sm font-medium rounded-es-xl bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none">
                    Details
                </button>
                <button onClick={onEditClick} className="w-full py-3 px-4 inline-flex justify-center items-center gap-x-2 text-sm font-medium rounded-ee-xl bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none">
                    Edit
                </button>
            </div>
        </div>
    );
};

export default Card;