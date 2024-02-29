import { ShoppingCart } from 'lucide-react';
import React from 'react';

export default function Cart({ cartLength }) {
  return (
    <div className='relative mr-3'>
      <span className='absolute -right-2 -top-2 inline-block h-5 w-5 rounded-full bg-gray-300 text-center text-xs font-medium leading-5 text-black'>
        {cartLength}
      </span>
      <ShoppingCart className='' size={'24px'} />
    </div>
  );
}
