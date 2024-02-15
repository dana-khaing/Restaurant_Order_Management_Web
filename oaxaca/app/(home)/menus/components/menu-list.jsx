'use client';

import { useEffect, useState } from 'react';
import MenuCard from './menu-card';
import { SERVICE_URLS } from '@/app/constants';

function MenuList() {
  const [menus, setMenus] = useState([]);

  useEffect(() => {
    async function fetchMenus() {
      const response = await fetch(`${SERVICE_URLS.MENU_SERVICE}/menu`);
      const data = await response.json();
      setMenus(data);
    }
    fetchMenus();
  }, []);

  return (
    <div className='w-4/5 grid lg:grid-cols-5 gap-3 md:grid-cols-3'>
      {menus.map((menu) => (
        <MenuCard key={menu.id} menu={menu} />
      ))}
    </div>
  );
}

export default MenuList;
