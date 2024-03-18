import React from "react";
import AuthNav from "./components/AuthNav";

function AuthLayout({ children }) {
  return (
    <main className="flex flex-col justify-center w-full items-center gap-6 lg:flex-row">
      <section className="flex flex-col justify-center items-center w-full h-full gap-8 text-center lg:items-start p-8">
        <AuthNav />

        {children}
      </section>
    </main>
  );
}

export default AuthLayout;
