import {
  DialogDescription,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog';

export default function MenuModal({ menu: { name, price, allergens } }) {
  return (
    <DialogHeader>
      <DialogTitle>{name}</DialogTitle>
      <DialogDescription>
        <img
          src='/images/burrito.jpeg'
          className='w-full h-52 rounded-xl my-4'
        />
        <div className='flex items-center justify-between px-1'>
          <span className='font-bold text-xl text-black line-clamp-1'>
            {name}
          </span>
          <span className='text-gray-600'>£{price}</span>
        </div>

        <div className='flex justify-between items-end'>
          <div className='flex gap-3 items-center text-xl text-black mt-4'>
            <span className='bg-gray-300 rounded-xl px-3 py-1 cursor-pointer'>
              -
            </span>
            <span className=''>1</span>
            <span className='bg-gray-300 rounded-xl px-3 py-1 cursor-pointer'>
              +
            </span>
          </div>
          <button className='bg-red-600 px-3 py-2 text-white rounded-lg'>
            Add to Cart
          </button>
        </div>
      </DialogDescription>
    </DialogHeader>
  );
}
