import React, { useState } from 'react';
import MenuModal from './menu-modal';
import { cn } from '@/lib/utils';

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