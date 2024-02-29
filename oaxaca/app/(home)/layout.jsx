import { Button } from '@/components/ui/button';
import Link from 'next/link';
import Cart from './components/cart';
import { fetchCart } from '../actions/cart';

export default async function HomeLayout({ children }) {
  const cart = await fetchCart();
  console.log(cart);

  return (
    <div>
      <div className='bg-[#EF3C3C] flex justify-between items-center p-4'>
        <span className='text-2xl text-white tracking-wider'>OAXACA</span>
        <div className='flex gap-3 items-center'>
          <Cart cartLength={cart.items?.length} />
          <Link className='text-white text-lg font-semibold' href='/'>
            Log In
          </Link>
          <Button>Book a Table</Button>
        </div>
      </div>
      {children}
    </div>
  );
}
