import React, { useState } from 'react';
import MenuCard from './menu-card';
import { SERVICE_URLS } from '@/app/constants';

function EditableMenuItem(item) { 
    const { id, category, name, description, price, allergens, calories, availability } = item;
    const [activeFilters, setActiveFilters] = useState({
      availability: false
  } );
    const toggleFilter = (filter) => {
    setActiveFilters((prevFilters) => ({
      ...prevFilters,
      [filter]: !prevFilters[filter],
    }));
  };
  const updateItemAvailability = async () => {
    const updatedItem = { ...item, availability: false };

    try {
      const response = await fetch(`${SERVICE_URLS.MENU_SERVICE}/menu/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedItem),
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
    } catch (error) {
      console.error("Failed to update item:", error);
    }
  };
  return (
    <div>
      <button onClick={updateItemAvailability}>Update Availability</button>
    </div>
  );
}

export default EditableMenuItem;

