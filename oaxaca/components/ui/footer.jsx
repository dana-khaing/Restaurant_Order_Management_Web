import React from "react";
import { Facebook } from "lucide-react";
import { Linkedin } from "lucide-react";
import { Twitter } from "lucide-react";
import { Instagram } from "lucide-react";

const footers = [
  {
    title: "Contact Support",
    link: "/contact-support",
  },
  {
    title: "Careers",
    link: "/careers",
  },
  {
    title: "Privacy Notice",
    link: "/privacy-notice",
  },
];

const Links = [
  {
    title: "Our Values",
    link: "/our-values",
  },
  {
    title: "Terms of Use",
    link: "/terms-of-use",
  },
  {
    title: "Privacy Policy",
    link: "/privacy-policy",
  },
  {
    title: "Contact Us",
    link: "/contact-us",
  },
  {
    title: "FAQs",
    link: "/faqs",
  },
];

export default function Footer() {
  return (
    <footer className="bg-[#EF3C3C] text-white text-center py-5 px-5 mt-5">
      <div className="flex m-5 divide-x-2">
        <div className="flex-1">
          {footers.map((footer) => (
            <div
              key={footer.title}
              className="my-5 px-24 text-xl font-bold text-end"
            >
              <a href={footer.link}>{footer.title}</a>
            </div>
          ))}
        </div>
        <div className="items-center flex-1 ">
          {Links.map((link) => (
            <div key={link.title} className="m-3 text-center font-bold text-lg">
              <a href={link.link}>{link.title}</a>
            </div>
          ))}
        </div>
        <div className="flex-1">
          <div className=" text-center font-bold text-lg">CONNECT WITH US</div>

          <div className="flex justify-evenly items-center h-36">
            <a href="/">
              <Instagram size={40} />
            </a>
            <a href="">
              <Facebook
                size={40}
                className="border-2 rounded-md px-[0.15rem]"
              />
            </a>
            <a href="">
              <Linkedin
                size={40}
                className="border-2 rounded-md px-[0.15rem]"
              />
            </a>
            <a href="">
              <Twitter size={40} />
            </a>
          </div>
          <div>
            <p>&copy; 2024 OAXACA Restaurant</p>
          </div>
        </div>
      </div>
    </footer>
  );
}
