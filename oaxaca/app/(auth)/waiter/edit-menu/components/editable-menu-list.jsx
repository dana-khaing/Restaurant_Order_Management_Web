'use client';

import { useEffect, useState } from 'react';
import { SERVICE_URLS } from '@/app/constants';
import EditableMenuItem from './editable-menu-card';

function EditableMenuList({ dummyAllergens, selectedFilters }) {
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
    <div className='w-5/6 grid lg:grid-cols-4 gap-8 md:grid-cols-3 sm:grid-cols-2 justify-center'>
      {menus
        .filter(
          (m) =>
            !m.allergens.some((allergen) =>
              selectedFilters.includes(allergen.id)
            )
        )
        .map((menu) => (
          <EditableMenuItem key={menu.id} menu={menu} />
        ))}
    </div>
  );
}

export default EditableMenuList;
