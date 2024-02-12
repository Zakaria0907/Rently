import React from 'react';
import { useCart } from '../../context/CartContext';
import { ArrowLeftIcon, ArrowRightIcon } from '@chakra-ui/icons';

const ShowCartButton: React.FC = () => {
    const { isCartVisible, toggleCartVisibility } = useCart();

    return (
        <div className="absolute top-20 right-4 z-30 cursor-pointer">
            {isCartVisible ? (
                <ArrowRightIcon onClick={toggleCartVisibility} />
            ) : (
                <ArrowLeftIcon onClick={toggleCartVisibility} />
            )}
        </div>
        )
};

export default ShowCartButton;
