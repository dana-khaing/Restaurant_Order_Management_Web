import MenuList from './components/menu-list';
import MenuFilters from './components/menu-filter';

function MenuPage() {
  const dummyAllergens = [
    {
      id: 1,
      name: 'Gluten',
    },
    {
      id: 2,
      name: 'Shellfish',
    },
    {
      id: 3,
      name: 'Nuts',
    },
    {
      id: 4,
      name: 'Dairy',
    },
    {
      id: 5,
      name: 'None',
    },
  ];
  return (
    <div className='flex gap-2 p-4'>
      <MenuList dummyAllergens={dummyAllergens} />
      <MenuFilters
        filters={dummyAllergens.slice(0, -1).map((a) => `${a.name}-free`)}
      />
    </div>
  );
}

export default MenuPage;
