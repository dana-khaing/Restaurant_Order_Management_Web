import Image from "next/image";

export default function AuthBanner(){

    return(
        <section className="hidden md:flex justify-center items-center w-full h-full">
                <Image
                    src={"/images/auth_banner.jpeg"}
                    width={500}
                    height={500}
                    className="object-fit"
                    alt="Banner for the authentication pages."
                />
            </section>
    );
}
