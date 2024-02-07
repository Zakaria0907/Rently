import React, { createContext, useState, useContext, ReactNode, FunctionComponent } from 'react';

interface CartContextType {
    isCartVisible: boolean;
    toggleCartVisibility: () => void;
    cartItems: Property[];
    removeItem: (id: string) => void; // Add this line to include removeItem in the context type
  }

const CartContext = createContext<CartContextType | undefined>(undefined);

interface CartProviderProps {
  children: ReactNode;
}

interface Property {
    id: string;
    name: string;
    price: number;
    image: string;
}

const tempcartItems = [
    { id: "1", name: "Cozy Apartment", price: 1200, image: "/path/to/image" },
    // Add more items as needed
];

export const CartProvider: FunctionComponent<CartProviderProps> = ({ children }) => {
    const [isCartVisible, setIsCartVisible] = useState<boolean>(false);
    const [cartItems, setCartItems] = useState<Property[]>([]);

    const removeItem = (id: string) => {
        console.log("remove item: " + id);
        setCartItems(currentItems => currentItems.filter(item => item.id !== id));
    };

    const toggleCartVisibility = () => {
        setCartItems(tempcartItems); //TEMPORARY, RE-PRESSING HIDE CART RESETS THE CART ITEMS
        setIsCartVisible(!isCartVisible);
    };

    return (
        <CartContext.Provider value={{ isCartVisible, cartItems, toggleCartVisibility, removeItem }}>
            {children}
        </CartContext.Provider>
    );
};

// Exporting useCart Hook with TypeScript
export const useCart = (): CartContextType => {
    const context = useContext(CartContext);
    if (context === undefined) {
        throw new Error('useCart must be used within a CartProvider');
    }
    return context;
};