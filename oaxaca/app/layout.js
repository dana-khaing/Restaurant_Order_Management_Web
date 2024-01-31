import { Inter } from 'next/font/google';
import './globals.css';
import { Button } from '@/components/ui/button';
import Link from 'next/link';

const inter = Inter({ subsets: ['latin'] });

export const metadata = {
  title: 'OAXACA Restaurant',
  description: '',
};

export default function RootLayout({ children }) {
  return (
    <html lang='en'>
      <body className={inter.className}>
        <div className='bg-[#EF3C3C] flex justify-between items-center p-4'>
          <span className='text-2xl text-white tracking-wider'>OAXACA</span>
          <div className='flex gap-3 items-center'>
            <Link className='text-white text-lg font-semibold' href='/'>
              Log In
            </Link>
            <Button>Book a Table</Button>
          </div>
        </div>
        {children}
      </body>
    </html>
  );
}
