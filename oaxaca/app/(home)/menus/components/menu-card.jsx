import React from 'react';

function MenuCard({ menu: { name, description, price } }) {
  return (
    <div className='w-52 border border-[#EF3C3C] rounded-xl'>
      <img src='/images/burrito.jpeg' className='w-full h-36 rounded-t-xl' />
      <div className='p-2'>
        <h5 className='text-md font-semibold'>{name}</h5>
        <span className='text-xs px-1 bg-green-500 capitalize rounded-md'>
          {description}
        </span>
        <button className='bg-[#EF3C3C] text-white rounded-lg px-7 py-1 block mx-auto mt-2'>
          Detail
        </button>
      </div>
    </div>
  );
}

export default MenuCard;
