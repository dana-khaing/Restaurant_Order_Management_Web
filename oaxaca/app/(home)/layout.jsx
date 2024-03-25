import { NavList } from './components/nav_list';
import { fetchCart } from '../actions/cart';
import Footer from './components/footer';
import UserProfile from './components/user-profile';

export default async function HomeLayout({ children }) {
  const cart = await fetchCart();
  console.log('Cart:', cart);
  const cartItems = cart?.items;

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
        <UserProfile cartItems={cartItems} />
      </div>
      {children}
      <Footer />
    </div>
  );
}
