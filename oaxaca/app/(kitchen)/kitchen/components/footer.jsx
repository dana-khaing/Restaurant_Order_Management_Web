"use client";
import React, { useState, useEffect } from "react";

export default function Footer({ serverTime }) {
  const [currentTime, setCurrentTime] = useState(serverTime);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentTime(new Date().toLocaleTimeString());
    }, 1000);
    return () => clearInterval(interval);
  }, []);

  return (
    <div className=" flex justify-center gap-2 items-center h-20 bg-[#5D5252] p-5 text-white font-semibold text-xl">
      <span>Current </span>
      <span>Time</span>
      <span>-</span>
      <span id="currentTime">
        {currentTime.split(":").map((segment, index) => (
          <span key={index}>
            {index > 0 && <span className="mx-2">:</span>}
            {segment}
          </span>
        ))}
      </span>
    </div>
  );
}
