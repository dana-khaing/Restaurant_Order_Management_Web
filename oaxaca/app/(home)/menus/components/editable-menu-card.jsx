import React, { useState } from 'react';
import MenuCard from './menu-card';
import { SERVICE_URLS } from '@/app/constants';

function EditableMenuItem(item) { 
    const { id, category, name, description, price, allergens, calories, availability } = item;
    const response = fetch(`${SERVICE_URLS.MENU_SERVICE}/menu/${id}`, {
      method: 'PUT',
      headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(item),
  });
  const updateItemAvailability = async () => {
    // Clone item and update availability
    const updatedItem = { ...item, availability: false };

    try {
      const response = await fetch(`${SERVICE_URLS.MENU_SERVICE}/menu/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedItem),
      });
    } catch (error) {
      console.error("Failed to update item:", error);
    }
  };
  const [activeFilters, setActiveFilters] = useState({
    availability: false
} );
  
const toggleFilter = (filter) => {
  setActiveFilters((prevFilters) => ({
    ...prevFilters,
    [filter]: !prevFilters[filter],
  }));
}; 
}

