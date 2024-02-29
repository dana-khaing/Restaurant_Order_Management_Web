import Link from "next/link";
import CallWaiterButton from "../custom_components/call-watier-btn";

export default function HomeLayout({ children }) {
  return (
    <div>
      <div className="bg-[#EF3C3C] flex justify-between items-center p-4">
        <span className="text-2xl text-white tracking-wider">OAXACA</span>
        <div className="flex gap-3 items-center">
          <CallWaiterButton />
          <Link className="text-white text-lg font-semibold" href="/">
            Log In
          </Link>
        </div>
      </div>
      {children}
    </div>
  );
}
