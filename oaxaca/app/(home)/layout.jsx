import { NavList } from './components/nav_list';
import { fetchCart } from '../actions/cart';
import Footer from './components/footer';
import UserProfile from './components/user-profile';
import CallWaiterButton from '../custom_components/call-watier-btn';
import Cart from './components/cart';
import { UserRound } from 'lucide-react';

export default async function HomeLayout({ children }) {
  const cartItems = await fetchCart();
  console.log("Fetch cart items", cartItems)
    
  return (
    <div>
      <div className='bg-[#EF3C3C] text-white text-lg font-medium flex justify-between items-center p-4'>
        <a href='/'>
          <img
            src='images/logo_sample.png'
            alt='logo'
            className=' flex justify-center align-middle w-[10rem] h-[2.5rem] '
          />
        </a>
        <div>
          <NavList />
        </div>
        <div className="flex gap-3 items-center">
          <CallWaiterButton />
          <Cart cartItems={cartItems} />
          <UserRound />
          <a href="/customer/login">Login</a>
        </div>
      </div>
      {children}
      <Footer />
    </div>
  );
}
