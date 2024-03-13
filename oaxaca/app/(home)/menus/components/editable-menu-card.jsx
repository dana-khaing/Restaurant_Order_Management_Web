import React, { useState } from 'react';
import MenuCard from './menu-card';
import { SERVICE_URLS } from '@/app/constants';

function EditableMenuItem() { 
  const response = fetch(`${SERVICE_URLS.MENU_SERVICE}/menu/${id}`);

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