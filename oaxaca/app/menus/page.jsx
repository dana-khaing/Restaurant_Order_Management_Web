import React from 'react';
import MenuList from './components/menu-list';

function MenuPage() {
  return (
    <div className='flex p-4'>
      <MenuList />
      <div className='w-1/5 h-96 border border-red-300'></div>
    </div>
  );
}

export default MenuPage;
