import React, { useState } from 'react';

const OrderingUI = () => {
 const [cartItems, setCartItems] = useState([]);

 const addToCart = (item) => {
    setCartItems([...cartItems, item]);
    };
  const removeFromCart = (itemId) => {
    setCartItems(cartItems.filter((item) => item.id !== itemId));
    };
}