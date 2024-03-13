import React, { useState } from 'react';
import MenuCard from './menu-card';
import { SERVICE_URLS } from '@/app/constants';

function EditableMenuItem(item) { 
  const response = fetch(`${SERVICE_URLS.MENU_SERVICE}/menu/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(item),
  });

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