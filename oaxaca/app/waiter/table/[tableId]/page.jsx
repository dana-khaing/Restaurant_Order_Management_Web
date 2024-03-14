import React from 'react';

export default function TableDetailPage({ params }) {
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
      <div className='mt-10 grid grid-cols-5 mx-32'>
        {categories.map((category, idx) => (
          <a
            href={`/waiter/table/${
              params.tableId
            }/${category.name.toLowerCase()}`}
            key={idx}
            className='text-white cursor-pointer px-6 mx-auto py-5 w-fit h-fit mb-2 rounded-3xl bg-[#EF3C3C]'
          >
            {category.name}
          </a>
        ))}
      </div>
    </div>
  );
}
