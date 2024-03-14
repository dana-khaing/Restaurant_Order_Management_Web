import React, { useState } from 'react';
import MenuCard from '../../../../(home)/menus/components/menu-card';
import { SERVICE_URLS } from '@/app/constants';
import { Dialog, DialogContent, DialogTrigger } from '@/components/ui/dialog';
import { cn } from '@/lib/utils';
import MenuModal from '@/app/(home)/menus/components/menu-modal';
import { Cross1Icon } from '@radix-ui/react-icons';
function EditableMenuItem({ menu }) {
  const { id, category, name, description, price, allergens, calories, availability } = menu;
  console.log(menu)
  const [activeFilters, setActiveFilters] = useState({
    availability: false
  });

  const toggleFilter = (filter) => {
    setActiveFilters((prevFilters) => ({
      ...prevFilters,
      [filter]: !prevFilters[filter],
    }));
  };
  const updateItemAvailability = async () => {
    const updatedItem = { ...item, availability: false };

    try {
      const response = await fetch(`${SERVICE_URLS.MENU_SERVICE}/menu/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedItem),
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
    } catch (error) {
      console.error("Failed to update item:", error);
    }
  };
  return (
    <div className='relative max-w-64 min-w-44 border border-[#EF3C3C] rounded-xl flex flex-col'>
      <img src='/images/burrito.jpeg' className='w-full h-40 rounded-t-xl' />
      <Cross1Icon className='absolute right-2 top-2'/>
      <div className='p-2 flex-1 flex flex-col'>
        <h5 className='text-md font-semibold line-clamp-1'>{name}</h5>
        <span className='text-gray-600 text-sm'>£{price}</span>

        <div className='my-1'>
          {allergens?.map((allergen) => (
            <span
              key={allergen.id}
              className='text-xs mr-1 p-1 bg-green-500 capitalize rounded-md'
            >
              {allergen.name}
            </span>
          ))}
        </div>

        <p className='line-clamp-1 text-sm mb-2 text-gray-500'>{description}</p>

        <Dialog>
          <DialogTrigger asChild>
            <button className='mt-auto bg-[#EF3C3C] text-white rounded-lg px-7 py-1 block mx-auto'>
              Detail
            </button>
          </DialogTrigger>
          <DialogContent
            className={cn(
              'top-[50%] max-h-[800px] w-[25%] overflow-auto shadow-xl'
            )}
          >
            <MenuModal menu={menu} />
          </DialogContent>
        </Dialog>
      </div>
    </div>
  );
}

export default EditableMenuItem;

