'use client';

import { useEffect, useState } from 'react';
import MenuCard from './menu-card';
import { SERVICE_URLS } from '@/app/constants';

// ['Gluten-free', 'Shellfish-free', 'Nut-free', 'Dairy-free', 'No Allergens'];

function MenuList({ dummyAllergens }) {
  const [menus, setMenus] = useState([]);

  useEffect(() => {
    async function fetchMenus() {
      const response = await fetch(`${SERVICE_URLS.MENU_SERVICE}/menu`);
      const data = await response.json();
      if (response.ok) {
        setMenus(
          data.map((d) => {
            const allergenIds = d.allergens.map((allergen) =>
              dummyAllergens.find((a) => a.name === allergen)
            );
            return {
              ...d,
              allergens: allergenIds,
            };
          })
        );
      }
    }
    fetchMenus();
  }, []);

  return (
    <div className='w-5/6 grid lg:grid-cols-5 gap-3 md:grid-cols-3 sm:grid-cols-2'>
      {menus.map((menu) => (
        <MenuCard key={menu.id} menu={menu} />
      ))}
    </div>
  );
}

export default MenuList;
