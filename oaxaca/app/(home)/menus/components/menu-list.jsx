'use client';

import React, { useEffect, useState } from 'react';
import MenuCard from './menu-card';

function MenuList() {
  // const menuItems = [
  //   { name: 'Burrito', diet: 'vegan' },
  //   { name: 'Salad', diet: 'vegetarian' },
  //   { name: 'Pasta', diet: 'gluten-free' },
  //   { name: 'Smoothie', diet: 'vegan' },
  //   { name: 'Grilled Chicken', diet: 'protein-rich' },
  //   { name: 'Sushi Roll', diet: 'pescatarian' },
  //   { name: 'Fruit Salad', diet: 'vegan' },
  //   { name: 'Tofu Stir-Fry', diet: 'vegan' },
  //   { name: 'Baked Fish', diet: 'pescatarian' },
  //   { name: 'Veggie Wrap', diet: 'vegetarian' },
  //   { name: 'Omelette', diet: 'protein-rich' },
  //   { name: 'Mango Salsa Chicken', diet: 'protein-rich' },
  //   { name: 'Avocado Toast', diet: 'vegetarian' },
  //   { name: 'Greek Salad', diet: 'vegetarian' },
  //   { name: 'Shrimp Scampi', diet: 'pescatarian' },
  //   { name: 'Eggplant Parmesan', diet: 'vegetarian' },
  //   { name: 'Tuna Salad', diet: 'pescatarian' },
  //   { name: 'Hummus Wrap', diet: 'vegan' },
  // ];

  const [menus, setMenus] = useState([]);

  useEffect(() => {
    async function fetchMenus() {
      const response = await fetch('http://localhost:8080/menu');
      const data = await response.json();
      setMenus(data);
    }
    fetchMenus();
  }, []);

  return (
    <div className='w-4/5 grid grid-cols-5 gap-3'>
      {menus.map((menu) => (
        <MenuCard key={menu.id} menu={menu} />
      ))}
    </div>
  );
}

export default MenuList;
