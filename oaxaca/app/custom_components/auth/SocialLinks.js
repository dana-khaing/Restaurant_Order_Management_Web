import { Label } from "@/components/ui/label";

import Image from "next/image";

export default function SocialLinks() {
    return (
        <div className="flex flex-col items-center gap-6">
            <div className="flex w-full justify-center items-center">
                <div className="h-0.5 w-full bg-gray-500" />
                <Label className="mx-6 text-nowrap">Or Login with</Label>
                <div className="h-0.5 w-full bg-gray-500" />
            </div>
            <div className="flex justify-evenly items-center gap-6 p-4">
                <div className="shadow px-8 py-2 border border-orange-500 flex justify-center items-center">
                    <Image
                        src="/images/google_logo.png"
                        width={20}
                        height={20}
                        className="object-fit aspect-square rounded-2xl"
                    />
                </div>

                <div className="shadow px-8 py-2 border border-orange-500 flex justify-center items-center">
                    <Image
                        src="/images/meta_logo.png"
                        width={20}
                        height={20}
                        className="object-fit aspect-square rounded-2xl"
                    />
                </div>

                <div className="shadow px-8 py-2 border border-orange-500 flex justify-center items-center">
                    <Image
                        src="/images/apple_logo.png"
                        width={20}
                        height={20}
                        className="object-fit aspect-square rounded-2xl"
                    />
                </div>
            </div>
        </div>
    );
}
