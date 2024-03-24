"use client";

import React from 'react';

// Create a context with an empty default value
const OrderContext = React.createContext({});

// Create a provider component
export const OrderProvider = ({ children }) => {
    const [order, setOrder] = React.useState(null);

    return (
        <OrderContext.Provider value={{ order, setOrder }}>
            {children}
        </OrderContext.Provider>
    );
};

export default OrderContext;