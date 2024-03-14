'use client';

import MenuList from '@/app/(home)/menus/components/menu-list';
import { useState } from 'react';

export default function TableDetailPage({ params }) {
  const [selectedCategory, setSelectedCategory] = useState(null);

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

  const [selectedFilters, setSelectedFilters] = useState([]);

  const categories = [
    {
      name: 'Main Course',
    },
    {
      name: 'Drinks',
    },
    {
      name: 'Desserts',
    },
    {
      name: 'Starters',
    },
    {
      name: 'Salads',
    },
    {
      name: 'Sides',
    },
  ];
  if (selectedCategory) {
    return (
      <div>
        <span className='bg-[#EF3C3C] px-6 py-3 rounded-2xl text-lg mt-5 block text-white w-fit mx-auto mb-5'>
          Table {params.tableId}
        </span>
        <MenuList
          selectedFilters={selectedFilters}
          dummyAllergens={dummyAllergens}
        />
      </div>
    );
  }
  return (
    <div>
      <span className='bg-[#EF3C3C] px-6 py-3 rounded-2xl text-lg mt-5 block text-white w-fit mx-auto'>
        Table {params.tableId}
      </span>
      <div className='mt-10 grid grid-cols-5 gap-14 mx-32'>
        {categories.map((category, idx) => (
          <span
            onClick={() => setSelectedCategory(category)}
            key={idx}
            className='cursor-pointer h-44 w-44 inline-flex items-center justify-center mb-2 rounded-3xl border-2 border-[#EF3C3C]'
          >
            {category.name}
          </span>
        ))}
      </div>
    </div>
  );
}
