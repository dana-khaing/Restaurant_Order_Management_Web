import { Inter } from "next/font/google";
import "./globals.css";
import { Toaster } from "@/components/ui/toaster";
import Footer from "@/components/ui/footer";

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "OAXACA Restaurant",
  description: "",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body className={inter.className}>
        {children}
        <Toaster />
        <Footer></Footer>
      </body>
    </html>
  );
}
