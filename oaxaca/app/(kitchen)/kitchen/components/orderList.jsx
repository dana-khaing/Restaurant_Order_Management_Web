import React from "react";

export default function OrderList() {
  return (
    <div className="grid grid-cols-5">
      <div className="  w-[100%] border-2 border-[#786c6c] flex-col m-4 p-4 items-center">
        <h1 className=" text-center underline pb-4 font-semibold text-[#5D5252]">
          Table 17
        </h1>
        <div>Burrito</div>
        <div>Bowl</div>
        <div>Quesadilla</div>
        <div className="flex justify-center align-middle items-center m-5">
          <button className=" bg-[#5D5252] text-white text-center p-3 rounded-[0.8rem] ">
            Bump Order
          </button>
        </div>
        <div className="flex justify-end text-sm gap-2 ">
          <p>Promise Time :</p>
          <p> 00 : 00 </p>
        </div>
      </div>
    </div>
  );
}
