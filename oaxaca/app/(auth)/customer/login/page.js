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
import SocialLinks from "@/app/custom_components/auth/SocialLinks";
import AuthNav from "@/app/custom_components/auth/AuthNav";
import AuthHeader from "@/app/custom_components/auth/AuthHeader";
import AuthBanner from "@/app/custom_components/auth/AuthBanner";

export default function CustomerLoginPage() {
    const form = useForm({
        defaultValues: {
            username: "",
            password: "",
            remember_me: false,
        },
        resolver: zodResolver(
            z.object({
                username: z.string().min(4).max(16),
                password: z.string().min(8).max(32),
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
                <AuthNav />

                <AuthHeader text={"Login"}/>

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
                                href={"/customer/signup"}
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

                        <SocialLinks />
                    </form>
                </Form>

                <Toaster />
            </section>

            <AuthBanner />
        </main>
    );
}
