import React from 'react';
import { Link } from 'react-router-dom';
import { useCart } from '../../context/CartContext';
import { DeleteIcon } from '@chakra-ui/icons'

const Cart: React.FC = () => {
    const { isCartVisible, cartItems, removeItem } = useCart();

    return (
            <div>
                {isCartVisible && (
                    // <div className="absolute right-0 top-0 bottom-0 w-96 p-4 bg-white shadow-lg overflow-y-auto">
                        // <div className="absolute right-0 top-0 w-96 h-full bg-white shadow-lg p-4 overflow-y-auto">
                    <div className='bg-white shadow-lg p-4'>
                        <h2 className="text-xl font-bold text-gray-800">Your Selections</h2>
                        {cartItems.length === 0 ? (
                            <p className="text-gray-600">Your cart is empty.</p>
                        ) : (
                            cartItems.map((item) => (
                            <div key={item.id} className="flex items-center justify-between my-4">
                                <div className="flex items-center">
                                    <img src={item.image} alt={item.name} className="w-20 h-20 object-cover mr-4" />
                                    <div>
                                        <h3 className="text-lg font-semibold">{item.name}</h3>
                                        <p className="text-gray-600">${item.price}</p>
                                    </div>
                                </div>
                                <DeleteIcon 
                                    onClick={() => removeItem(item.id)}
                                    className="text-red-500 hover:text-red-700"
                                >

                                </DeleteIcon>
                                </div>
                                ))
                            )}
                            <Link
                                to="/checkout"
                                className="mt-6 px-5 py-2 block text-center text-white bg-blue-500 rounded-md hover:bg-blue-700"
                            >
                                Proceed to Checkout
                            </Link>
                    </div>
                )}
            </div>
    );
};

export default Cart;
