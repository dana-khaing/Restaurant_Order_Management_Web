'use client';

import { UserRound } from 'lucide-react';
import { useContext, useEffect } from 'react';
import { toast } from '@/components/ui/use-toast';
import { useRouter } from 'next/navigation';
import { AuthContext } from '../providers/auth';

export default function WaiterLayout({ children }) {
  const { logoutUser } = useContext(AuthContext);

  const router = useRouter();
  useEffect(() => {
    async function validateRememberMeToken() {
      try {
        const response = await fetch('/api/auth/waiter/login/validate', {
          method: 'GET',
          headers: { 'Content-Type': 'application/json' },
        });

        // First, check if the response is OK
        if (!response.ok) {
          const errorText = await response.text(); // Get the error message as text
          console.error('Error:', errorText);
          toast({
            title: 'Sign up failed.',
            description: 'Please try again.',
          });

          router.push('/waiter/login');
        }

        // Then, safely parse the JSON
        const data = await response.json();
        console.log('Success:', data);
        toast({
          title: 'Logged in successfully',
          description: 'Redirecting to home page.',
        });

        router.push('/waiter/dashboard');
      } catch (error) {
        console.error('An error occurred:', error);
        toast({
          title: 'Sign up failed.',
          description: 'Please try again.',
        });

        router.push('/waiter/login');
      }
    }

    validateRememberMeToken();
  }, []);

  const handleLogout = () => {
    fetch(`/api/auth/waiter/logout`).then((res) => {
      console.log(res);
      logoutUser();
    });
  };

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
          <button
            className='bg-white text-red-500 px-3 py-2 rounded-lg'
            onClick={handleLogout}
          >
            Logout
          </button>
        </div>
      </div>
      {children}
    </div>
  );
}
