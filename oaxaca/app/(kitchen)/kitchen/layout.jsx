import React from "react";
import { UserRound } from "lucide-react";
import Footer from "./components/footer";

const KitchenLayOut = ({ children }) => {
  const serverTime = new Date().toLocaleTimeString();
  return (
    <div>
      <div className="bg-[#5D5252] text-white text-lg font-medium flex justify-between items-center p-4 w-[100%]">
        <a href="/">
          <img
            src="images/logo_sample.png"
            alt="logo"
            className=" flex justify-center align-middle w-[10rem] h-[2.5rem] "
          />
        </a>

        <div className="flex gap-3 items-center">
          <UserRound />
          <a href="/customer/login">Login</a>
        </div>
      </div>
      {children}
      <Footer serverTime={serverTime} />
    </div>
  );
};

export default KitchenLayOut;
