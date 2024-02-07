import React from 'react';
import tw from 'tailwind-styled-components';
import { useCart } from '../../context/CartContext';
import Cart from './Cart';
import ShowCartButton from './ShowCart';

const SideBar: React.FC = () => {
    const { isCartVisible } = useCart(); // Use the useCart hook to get the cart visibility state
    return (
            <div>
                <ShowCartButton/>
                {isCartVisible && (
                    <Cart/>
                )}
            </div>
        )
};

export default SideBar;
