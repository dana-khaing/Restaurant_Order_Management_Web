'use client';

import { addToCart, fetchCart, updateCartItem } from '@/app/actions/cart';
import {
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogClose,
} from '@/components/ui/dialog';
import { useState } from 'react';

export default function MenuModal({ menu }) {
  const { name, price, allergens, calories, category, description, imageURL } = menu;

  const [quantity, setQuantity] = useState(1);
  const incrementQuantity = () => setQuantity((prev) => prev + 1);
  const decrementQuantity = () => {
    if (quantity > 1) setQuantity((prev) => prev - 1);
  };



  const handleAddCartItem = async () => {
    const cartItems = (await fetchCart())?.items;
    console.log(cartItems);

    const existingItem = cartItems?.find((item) => item.productId === menu.id);

    if (existingItem) {
      await updateCartItem(menu.id, existingItem.quantity + quantity);
    } else {
      console.log('adding to cart');
      await addToCart({
        productId: menu.id,
        quantity : quantity,
        price : price,
        productName: name,
        allergens: allergens.map((a) => a.name),
        calories: calories,
        category: category,
        description: description,
        imageUrl: imageURL,
      });
    }
  };
  return (
    <DialogHeader>
      <DialogTitle>{name}</DialogTitle>
      <DialogDescription>
        <img src={imageURL} className='w-full h-52 rounded-xl my-4' />
        <div className='flex items-center justify-between px-1'>
          <span className='font-bold text-xl text-black line-clamp-1'>
            {name}
          </span>
          <span className='text-gray-600'>£{price}</span>
        </div>
        


        <div className='flex justify-between items-end'>
          <div className='flex gap-3 items-center text-xl text-black mt-4'>
            <button
              onClick={decrementQuantity}
              className='bg-gray-300 rounded-xl px-3 py-1'
            >
              -
            </button>
            <span className=''>{quantity}</span>
            <button
              onClick={incrementQuantity}
              className='bg-gray-300 rounded-xl px-3 py-1'
            >
              +
            </button>
          </div>

          <DialogClose asChild>
            <button
              onClick={handleAddCartItem}
              className='bg-red-600 px-3 py-2 text-white rounded-lg'
            >
              Add to Cart
            </button>
          </DialogClose>
        </div>
      </DialogDescription>
    </DialogHeader>
  );
}
