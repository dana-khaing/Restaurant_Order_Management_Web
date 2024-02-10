import React from 'react';
import AuthNav from './components/AuthNav';
import { Toaster } from '@/components/ui/toaster';
import AuthBanner from './components/AuthBanner';

function AuthLayout({ children }) {
  return (
    <main className='flex flex-col justify-center w-full items-center gap-6 lg:flex-row'>
      <section className='flex flex-col justify-center items-center w-full h-full gap-8 text-center lg:items-start p-8'>
        <AuthNav />

        {children}

        <Toaster />
      </section>

    </main>
  );
}

export default AuthLayout;
