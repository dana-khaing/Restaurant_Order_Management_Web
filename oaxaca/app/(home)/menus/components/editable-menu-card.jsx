import React, { useState } from 'react';
import MenuCard from './menu-card';
import { SERVICE_URLS } from '@/app/constants';

function EditableMenuItem() { 
  const [activeFilters, setActiveFilters] = useState({
    main_course: false
} );
const toggleFilter = (filter) => {
  setActiveFilters((prevFilters) => ({
    ...prevFilters,
    [filter]: !prevFilters[filter],
  }));
}
}