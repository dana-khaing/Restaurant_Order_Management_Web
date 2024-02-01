"use client";

import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import {
    Form,
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Checkbox } from "@/components/ui/checkbox";
import { Toaster } from "@/components/ui/toaster";
import { useToast } from "@/components/ui/use-toast";
import { Button } from "@/components/ui/button";
import Link from "next/link";
import Image from "next/image";
import { Label } from "@/components/ui/label";

export default function CustomerLoginPage() {
    const form = useForm({
        defaultValues: {
            username: "",
            password: "",
            remember_me: false,
        },
        resolver: zodResolver(
            z.object({
                username: z.string().min(4).max(16).optional(),
                password: z.string().min(8).max(32).optional(),
                remember_me: z.boolean().optional(),
            })
        ),
        mode: "onBlur",
    });

    function onSubmit(values) {
        // Do something with the form values.
        console.log(values);
        toast({
            title: "You submitted the following values:",
            description: (
                <pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-4">
                    <code className="text-white">
                        {JSON.stringify(values, null, 2)}
                    </code>
                </pre>
            ),
        });
    }

    const { toast } = useToast();

    return (
        <main className="flex flex-col justify-center w-full items-center gap-6 lg:flex-row">
            <section className="flex flex-col justify-center items-center w-full h-full gap-8 text-center lg:items-start p-8">
                <nav className="w-full flex justify-start items-center">
                    {" "}
                    <h2 className="font-bold text-xl text-orange-500">
                        {" "}
                        Oaxaca{" "}
                    </h2>{" "}
                </nav>

                <div className="flex flex-col justify-center items-center w-full gap-4 lg:items-start">
                    <h1 className="text-2xl font-semibold text-orange-500">
                        {" "}
                        Login{" "}
                    </h1>

                    <p className="text-gray-500">
                        {" "}
                        Login to access your Oaxaca account{" "}
                    </p>
                </div>

                <Form {...form}>
                    <form
                        onSubmit={form.handleSubmit(onSubmit)}
                        className="space-y-8 w-full text-start"
                    >
                        <FormField
                            control={form.control}
                            name="username"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Username</FormLabel>
                                    <FormControl>
                                        <Input
                                            placeholder="shadcn"
                                            {...field}
                                        />
                                    </FormControl>
                                    <FormDescription>
                                        This is your public display name.
                                    </FormDescription>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />

                        <FormField
                            control={form.control}
                            name="password"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Password</FormLabel>
                                    <FormControl>
                                        <Input
                                            placeholder="shadcn"
                                            type="password"
                                            {...field}
                                        />
                                    </FormControl>
                                    <FormDescription>
                                        This is your password you used to create
                                        your account.
                                    </FormDescription>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />

                        <FormField
                            control={form.control}
                            name="remember_me"
                            render={({ field }) => (
                                <FormItem className="flex flex-row items-center w-full justify-between space-x-3 space-y-0 rounded-md border p-4 shadow">
                                    <div className="flex justify-center items-center gap-2">
                                        <FormControl>
                                            <Checkbox
                                                checked={field.value}
                                                onCheckedChange={field.onChange}
                                            />
                                        </FormControl>
                                        <div className="space-y-1 leading-none">
                                            <FormLabel>Remember me</FormLabel>
                                        </div>
                                    </div>

                                    <div>
                                        <FormLabel className="text-red-500">
                                            Forgot your password?
                                        </FormLabel>
                                    </div>
                                </FormItem>
                            )}
                        />

                        <div className="flex justify-center gap-4 items-center w-full flex-col">
                            <Button type="submit" className="w-full">
                                <p>Login</p>
                            </Button>

                            <Link
                                href={"customer/signup"}
                                className="cursor-pointer"
                            >
                                <FormLabel>
                                    {" "}
                                    Don't have an account?{" "}
                                    <span className="text-red-500">
                                        {" "}
                                        Signup.{" "}
                                    </span>{" "}
                                </FormLabel>
                            </Link>
                        </div>
                    </form>
                </Form>
                <div className="flex flex-col items-center gap-6">
                    <div className="flex w-full justify-center items-center">
                        <div className="h-0.5 w-full bg-gray-500" />
                        <Label className="mx-6 text-nowrap">
                            Or Login with
                        </Label>
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

                <Toaster />
            </section>

            <section className="hidden md:flex justify-center items-center w-full h-full">
                <Image
                    src={"/images/auth_banner.jpeg"}
                    width={500}
                    height={500}
                    className="object-fit"
                />
            </section>
        </main>
    );
}
