'use client';

import { AuthContext } from '@/app/providers/auth';
import { UserRound } from 'lucide-react';
import { useContext } from 'react';
import Cart from './cart';
import CallWaiterButton from '@/app/custom_components/call-watier-btn';
import Link from 'next/link';
import { SERVICE_URLS } from '@/app/constants';

export default function UserProfile({ cartItems }) {
  const { user, logoutUser } = useContext(AuthContext);

  const handleLogout = () => {
    fetch(`/api/auth/customer/logout`).then((res) => {
      console.log(res);
      logoutUser();
    });
  };

  return (
    <div className='flex gap-3 items-center'>
      {user ? (
        <div className='flex gap-2 items-center'>
          <CallWaiterButton />
          <Cart cartItems={cartItems} />
          <h1>{user.username}</h1>
          <button
            className='bg-white text-red-500 px-3 py-2 rounded-lg'
            onClick={handleLogout}
          >
            Logout
          </button>
        </div>
      ) : (
        <>
          <UserRound />
          <Link
            className='bg-white text-red-500 px-3 py-2 rounded-lg'
            href='/customer/login'
          >
            Login
          </Link>
        </>
      )}
    </div>
  );
}
