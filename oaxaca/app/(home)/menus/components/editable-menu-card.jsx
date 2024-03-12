import React, { useState } from 'react';

function EditableMenuItem() { 
  const [activeFilters, setActiveFilters] = useState({
    menuItem : false
} );
const toggleFilter = (filter) => {
  setActiveFilters((prevFilters) => ({
    ...prevFilters,
    [filter]: !prevFilters[filter],
  }));
};
}
