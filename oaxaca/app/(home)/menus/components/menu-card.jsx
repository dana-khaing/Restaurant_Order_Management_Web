function MenuCard({
  menu: { name, description, price, allergens },
  dummyAllergens,
}) {
  console.log(allergens);
  return (
    <div className='max-w-56 min-w-44 border border-[#EF3C3C] rounded-xl flex flex-col'>
      <img src='/images/burrito.jpeg' className='w-full h-36 rounded-t-xl' />
      <div className='p-2 flex-1 flex flex-col'>
        <h5 className='text-md font-semibold line-clamp-1'>{name}</h5>
        <span className='text-gray-600 text-sm'>£{price}</span>

        <div className='my-1'>
          {allergens.map((allergen) => (
            <span className='text-xs mr-1 p-1 bg-green-500 capitalize rounded-md'>
              {allergen.name}
            </span>
          ))}
        </div>

        <p className='line-clamp-1 text-sm mb-2 text-gray-500'>{description}</p>

        <button className='mt-auto bg-[#EF3C3C] text-white rounded-lg px-7 py-1 block mx-auto'>
          Detail
        </button>
      </div>
    </div>
  );
}

export default MenuCard;
