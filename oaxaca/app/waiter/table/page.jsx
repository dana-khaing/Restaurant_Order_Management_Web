export default function TablePage() {
  return (
    <div>
      <span className='bg-[#EF3C3C] px-6 py-3 rounded-2xl text-lg mt-5 block text-white w-fit mx-auto'>
        Table List
      </span>
      <div className='mt-10 grid grid-cols-5 mx-32'>
        {Array.from({ length: 25 }).map((_, idx) => (
          <span
            key={idx}
            className='text-white cursor-pointer px-6 mx-auto py-5 w-fit h-fit mb-2 rounded-3xl bg-[#EF3C3C]'
          >
            {idx + 1 < 10 ? `0${idx + 1}` : idx + 1}
          </span>
        ))}
      </div>
    </div>
  );
}
