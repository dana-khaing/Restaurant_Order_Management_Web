import Link from "next/link";
import CallWaiterButton from "../custom_components/call-watier-btn";

export default function HomeLayout({ children }) {
  return (
    <div>
      <div className="bg-[#EF3C3C] flex justify-between items-center p-4">
        <span className="text-2xl text-white tracking-wider">OAXACA</span>
        <div className="flex gap-3 items-center">
          <CallWaiterButton />
          <Link href="/customer/login">
            <h4 className="text-white text-lg font-medium hover: ">Login</h4>
          </Link>
        </div>
      </div>
      {children}
    </div>
  );
}
