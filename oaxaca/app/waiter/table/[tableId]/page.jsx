'use client';

import { useState } from 'react';

export default function TableDetailPage({ params }) {
  const [selectedCategory, setSelectedCategory] = useState(null);

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
  return (
    <div>
      <span className='bg-[#EF3C3C] px-6 py-3 rounded-2xl text-lg mt-5 block text-white w-fit mx-auto'>
        Table {params.tableId}
      </span>
      <div className='mt-10 grid grid-cols-5 gap-14 mx-32'>
        {categories.map((category, idx) => (
          <span
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
