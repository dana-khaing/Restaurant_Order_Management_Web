import React from 'react';
import Footer from '@/app/(home)/components/footer';
import { NavList } from '@/app/(home)/components/nav_list';
import Cart from '@/app/(home)/components/cart';
import CallWaiterButton from '@/app/custom_components/call-watier-btn';
import { UserRound } from 'lucide-react';
import { fetchCart } from '@/app/actions/cart';


export default async function OrderLayout({children}){
    const cart = await fetchCart();
    return (
        <div>
            <div className="bg-[#EF3C3C] text-white text-lg font-medium flex justify-between items-center p-4">
                <a href="/">
                    <img
                        src="/images/logo_sample.png"
                        alt="logo"
                        className=" flex justify-center align-middle w-[10rem] h-[2.5rem] "
                    />
                </a>
                <div>
                    <NavList />
                </div>
                <div className="flex gap-3 items-center">
                    <CallWaiterButton />
                    <Cart cartItems={cart?.items} />
                    <UserRound />
                    <a href="/customer/login">Login</a>
                </div>
            </div>
            {children}
            <Footer />
        </div>
    );
}