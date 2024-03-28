'use client';

import { UserRound } from 'lucide-react';
import Footer from './components/footer';
import { useEffect } from 'react';
import { useToast } from '@/components/ui/use-toast';
import { useRouter } from 'next/navigation';

const KitchenLayOut = ({ children }) => {
  const { toast } = useToast();
  const router = useRouter();

  useEffect(() => {
    async function validateRememberMeToken() {
      try {
        const response = await fetch('/api/auth/kitchen_staff/login/validate', {
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

          router.push('/kitchen_staff/login');
        }

        // Then, safely parse the JSON
        const data = await response.json();
        console.log('Success:', data);
        toast({
          title: 'Logged in successfully',
          description: 'Redirecting to home page.',
        });

        router.push('/kitchen');
      } catch (error) {
        console.error('An error occurred:', error);
        toast({
          title: 'Sign up failed.',
          description: 'Please try again.',
        });

        router.push('/kitchen_staff/login');
      }
    }

    validateRememberMeToken();
  }, []);
  return (
    <div>
      <div className='bg-[#5D5252] text-white text-lg font-medium flex justify-between items-center p-4 w-[100%]'>
        <a href='/'>
          <img
            src='images/logo_sample.png'
            alt='logo'
            className=' flex justify-center align-middle w-[10rem] h-[2.5rem] '
          />
        </a>

        <div className='flex gap-3 items-center'>
          <UserRound />
          <a href='/customer/login'>Logout</a>
        </div>
      </div>
      {children}
      <Footer />
    </div>
  );
};

export default KitchenLayOut;
