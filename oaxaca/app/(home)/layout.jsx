import Link from "next/link";
import CallWaiterButton from "../custom_components/call-watier-btn";
import { NavList } from "./components/nav_list";
import { UserRound } from "lucide-react";
export default function HomeLayout({ children }) {
  return (
    <div>
      <div className="bg-[#EF3C3C] text-white text-lg font-medium flex justify-between items-center p-4">
        <a href="/">
          <img
            src="images/logo_sample.png"
            alt="logo"
            className=" flex justify-center align-middle w-[12.5rem] h-11 "
          />
        </a>
        <div>
          <NavList />
        </div>
        <div className="flex gap-3 items-center">
          <CallWaiterButton />
          <UserRound />
          <a href="/customer/login">Login</a>
        </div>
      </div>
      {children}
    </div>
  );
}
