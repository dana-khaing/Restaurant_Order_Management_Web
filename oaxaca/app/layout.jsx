import { Inter } from "next/font/google";
import "./globals.css";
import { Toaster } from "@/components/ui/toaster";
import { OrderProvider } from "./custom_components/context/OrderContext";

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
    title: "OAXACA Restaurant",
    description: "",
};

export default function RootLayout({ children }) {
    return (
        <html lang="en">
            <OrderProvider>
                <body className={inter.className}>
                    {children}
                    <Toaster />
                </body>
            </OrderProvider>
        </html>
    );
}
