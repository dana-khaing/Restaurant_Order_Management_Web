import React from 'react';

export default function MenuFilters({ filters }) {
  return (
    <div className='w-1/5 p-5 sm:w-1/6 h-96 border border-red-300 rounded-md'>
      {filters.map((filter) => (
        <div key={filter} className='flex items-center gap-2 text-gray-700'>
          <input type='checkbox' id={filter} name={filter} value={filter} />
          <label htmlFor={filter}>{filter}</label>
        </div>
      ))}
    </div>
  );
}
