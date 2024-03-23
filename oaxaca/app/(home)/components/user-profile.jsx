'use client';

import { AuthContext } from '@/app/providers/auth';
import { UserRound } from 'lucide-react';
import { useContext } from 'react';
import Cart from './cart';
import CallWaiterButton from '@/app/custom_components/call-watier-btn';
import Link from 'next/link';

export default function UserProfile({ cart }) {
  const { user, loginUser, logoutUser } = useContext(AuthContext);
  console.log(user);
  return (
    <div className='flex gap-3 items-center'>
      {user ? (
        <div className='flex gap-2 items-center'>
          <CallWaiterButton />
          <Cart cartItems={cart?.items} />
          <h1>{user.username}</h1>
          <button
            className='bg-white text-red-500 px-3 py-2 rounded-lg'
            onClick={logoutUser}
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
