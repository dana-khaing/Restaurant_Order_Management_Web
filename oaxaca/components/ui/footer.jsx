import React from "react";
import { Facebook } from "lucide-react";
import { Linkedin } from "lucide-react";
import { Twitter } from "lucide-react";
import { Instagram } from "lucide-react";

export default function Footer() {
  return (
    <footer className="bg-[#EF3C3C] text-white text-center py-5 px-5">
      <p>&copy; 2024 OAXACA Restaurant</p>

      <div className="flex justify-evenly items-center h-10 ">
        <a href="">
          <Instagram size={40} />
        </a>
        <a href="">
          <Facebook size={40} className="border-2 rounded-md px-[0.15rem]" />
        </a>
        <a href="">
          <Linkedin size={40} className="border-2 rounded-md px-[0.15rem]" />
        </a>
        <a href="">
          <Twitter size={40} />
        </a>
      </div>
    </footer>
  );
}
