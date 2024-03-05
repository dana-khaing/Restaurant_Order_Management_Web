import { UserRound } from 'lucide-react';
import { fetchCart } from '../actions/cart';

export default async function WaiterLayout({ children }) {
  const cart = await fetchCart();
  return (
    <div>
      <div className='bg-[#EF3C3C] text-white text-lg font-medium flex justify-between items-center p-4'>
        <a href='/'>
          <img
            src='/images/logo_sample.png'
            alt='logo'
            className=' flex justify-center align-middle w-[12.5rem] h-11 '
          />
        </a>
        <div className='flex gap-3 items-center'>
          <UserRound />
          <a href='/customer/login'>Login</a>
        </div>
      </div>
      {children}
    </div>
  );
}
