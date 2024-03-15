import { NavList } from "@/app/(home)/components/nav_list";
export default function DashboardLayout({ children }) {
    return (
        <main className="w-full min-h-full">
            <div className="bg-[#EF3C3C] text-white text-lg font-medium flex justify-between items-center p-4">
                <a href="/">
                    <img
                        src="images/logo_sample.png"
                        alt="logo"
                        className=" flex justify-center align-middle w-[10rem] h-[2.5rem] "
                    />
                </a>
                <div>
                    <NavList />
                </div>
            </div>
            {children}
        </main>
    );
}
