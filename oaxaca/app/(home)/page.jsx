import { Link } from "lucide-react";

export default function Home() {
  return (
    <div className="flex-col justify-center items-center px-5 py-5 bg-[#FFF5F2]">
      <div className="text-center font-black text-[#EF3C3C] text-7xl font-serif my-16">
        WELCOME TO OAXACA
      </div>
      <div className=" text-center my-10 px-10 text-xl">
        We invite you to indulge in an extraordinary fine dining experience
        unlike any other. Step into our elegant ambiance, where the warmth of
        Spanish hospitality meets the sophistication of modern gastronomy.
      </div>

      <div className="inline-flex items-center h-80 rounded-3xl  my-5 mx-20 border-2 border-[#EF3C3C]">
        <div className="flex-[2] px-5 py-5 justify-evenly">
          <h1 className="font-black text-3xl font-sans my-5 ml-5">
            Indulge in a Fiesta of Flavors: Secure Your Seat for a Culinary
            Extravaganza
          </h1>

          <p className="my-5 ml-5">
            {" "}
            Experience the magic of Oaxaca. Reserve your table now and embark on
            a culinary journey you won't soon forget.
          </p>
          <div className="flex justify-start text-center">
            <a
              className="bg-[#EF3C3C] justify-center items-center text-white px-3 py-3 w-36 h-12 rounded-lg my-0 mx-5 cursor-pointer"
              href="/menus"
            >
              BOOK TABLE
            </a>
          </div>
        </div>
        <div className="flex-1 justify-center items-center px-5 py-4 overflow-hidden w-full h-full ">
          <img
            src="/images/img1.jpg"
            className="relative w-full h-full rounded-3xl"
          />
        </div>
      </div>
      <div className="flex items-center jus h-80 rounded-3xl my-5 mx-20 border-2 border-[#EF3C3C]">
        <div className="flex-1 justify-center items-center px-5 py-4 overflow-hidden w-full h-full ">
          <img
            src="/images/img2.jpg"
            className="relative w-full h-full rounded-3xl"
          />
        </div>
        <div className="flex-[2] px-5 py-5 justify-evenly">
          <h1 className="font-black text-3xl font-sans my-5 ml-5">
            Commence a Culinary Adventure through the Exquisite Delights of
            Oaxaca
          </h1>

          <p className="my-5 ml-5">
            {" "}
            we invite you to indulge in an extraordinary fine dining experience
            unlike any other. Step into our elegant ambiance, where the warmth
            of Mexican hospitality meets the sophistication of modern
            gastronomy.
          </p>
          <div className="flex justify-end text-center">
            <a
              className="bg-[#EF3C3C] justify-center items-center text-white px-3 py-3 w-36 h-12 rounded-lg my-0 mx-5 cursor-pointer"
              href="/menus"
            >
              VIEW MENU
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}
