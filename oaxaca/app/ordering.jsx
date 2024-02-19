import React, { useState } from 'react';
import MenuList from './menu-list';

const OrderingUI = () => {
    const [cartItems, setCartItems] = useState([]);

    const addToCart = (item) => {
        setCartItems([...cartItems, item]);
    };
    const removeFromCart = (itemId) => {
        const updateCart = [...cartItems];
        updateCart.splice(itemId, 1);
        setCartItems(updateCart);
    };
    const getCartTotal = () => {
        return cartItems.reduce((total, item) => total + item.price, 0);
     };
    
return (
    <div>
      <h2>Menu</h2>
      <MenuList addToCart={addToCart} />
      
    </div>
  );
};

export default OrderingUI;