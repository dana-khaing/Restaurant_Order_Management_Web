'use client';

import MenuList from './components/menu-list';
import MenuFilters from './components/menu-filter';
import { useState } from 'react';

const dummyAllergens = [
  { id: 0, name: 'None' },
  {
    id: 1,
    name: 'Gluten',
  },
  {
    id: 2,
    name: 'Shellfish',
  },
  {
    id: 3,
    name: 'Nuts',
  },
  {
    id: 4,
    name: 'Dairy',
  },
];

function MenuPage() {
  const [selectedFilters, setSelectedFilters] = useState([]);

  return (
    <div className='flex gap-8 p-4 px-10'>
      <MenuList
        selectedFilters={selectedFilters}
        dummyAllergens={dummyAllergens}
      />
      <MenuFilters
        filters={dummyAllergens.slice(1).map((a) => ({
          id: a.id,
          name: `${a.name}-free`,
        }))}
        selectedFilters={selectedFilters}
        setSelectedFilters={setSelectedFilters}
      />
    </div>
  );
}

export default MenuPage;
