export default function MenuFilters({ filters, setSelectedFilters }) {
  return (
    <div className='p-5 sm:w-1/6 h-96 border border-red-300 rounded-md min-w-fit'>
      {filters.map((filter) => (
        <div key={filter.id} className='flex items-center gap-2 text-gray-700'>
          <input
            onChange={() => {
              setSelectedFilters((prev) =>
                prev.includes(filter.id)
                  ? prev.filter((f) => f !== filter.id)
                  : [...prev, filter.id]
              );
            }}
            type='checkbox'
            id={filter.id}
            name={filter.id}
            value={filter.id}
          />
          <label className='cursor-pointer' htmlFor={filter.id}>
            {filter.name}
          </label>
        </div>
      ))}
    </div>
  );
}
