import React from "react";
import SidePanel from "./components/sideBar";
export default function KitchenHome() {
  return (
    <div className="my-0 flex justify-evenly w-screen">
      <div className="w-[15%] my-2 ml-[-0.25rem] mr-5 h-[80vh] border-[#786c6c] border-2 px-5 py-5 flex-shrink-0">
        <SidePanel />
      </div>
      <div className="flex-1">
        <p className="py-5 mx-5 flex justify-center text-lg font-bold text-[#5D5252]">
          Order List
        </p>
        <div>Order</div>
      </div>
    </div>
  );
}
