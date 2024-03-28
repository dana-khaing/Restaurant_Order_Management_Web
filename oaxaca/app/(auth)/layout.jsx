'use client';

import { useEffect } from 'react';
import AuthNav from './components/AuthNav';
import { useRouter } from 'next/navigation';
import { useToast } from '@/components/ui/use-toast';

function AuthLayout({ children }) {
  const router = useRouter();

  const { toast } = useToast();

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
          return;
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

  return (
    <main className='flex flex-col justify-center w-full items-center gap-6 lg:flex-row'>
      <section className='flex flex-col justify-center items-center w-full h-full gap-8 text-center lg:items-start p-8'>
        <AuthNav />

        {children}
      </section>
    </main>
  );
}

export default AuthLayout;
