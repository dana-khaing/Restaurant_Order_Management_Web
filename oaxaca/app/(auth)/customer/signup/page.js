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
import { Toaster } from "@/components/ui/toaster";
import { useToast } from "@/components/ui/use-toast";
import { Button } from "@/components/ui/button";
import Link from "next/link";
import SocialLinks from "@/app/custom_components/auth/SocialLinks";
import AuthNav from "@/app/custom_components/auth/AuthNav";
import AuthHeader from "@/app/custom_components/auth/AuthHeader";
import AuthBanner from "@/app/custom_components/auth/AuthBanner";

export default function WaiterLoginPage() {
	const form = useForm({
		defaultValues: {
			username: "",
			password: "",
			email: "",
			name: ""
		
		},
		resolver: zodResolver(
			z.object({
				username: z.string({ required_error: "Username should be at least 4 characters" }).min(4).max(16),
				password: z.string({ required_error: "Password has to be a minimum of 8 characters" }).min(8).max(32),
				email: z.string({ required_error: "You need a valid email" }).max(320),
				name: z.string().min(6).max(60),
			
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

				<AuthHeader text={"Signup"} />

				<Form {...form}>
					<form
						onSubmit={form.handleSubmit(onSubmit)}
						className="space-y-8 w-full text-start"
					>


						<FormField
							control={form.control}
							name="email"
							render={({ field }) => (
								<FormItem>
									<FormLabel>Username</FormLabel>
									<FormControl>
										<Input
											placeholder="shadcn@shadcn.com"
											{...field}
										/>
									</FormControl>
									<FormDescription>
										This is your public display email. 
									</FormDescription>
									<FormMessage />
								</FormItem>
							)}
						/>

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
							name="name"
							render={({ field }) => (
								<FormItem>
									<FormLabel> Full Name </FormLabel>
									<FormControl>
										<Input
											placeholder="shadcn"
											type="firstName"
											{...field}
										/>
									</FormControl>

									<FormMessage />
								</FormItem>
							)}
						/>






						<div className="flex justify-center gap-4 items-center w-full flex-col">
							<Button type="submit" className="w-full">
								<p>Signup</p>
							</Button>

							<Link
								href={"/customer/login"}
								className="cursor-pointer"
							>
								<FormLabel>
									{" "}
									Have have an account?{" "}
									<span className="text-red-500" >
										{" "}
										Login.{" "}
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


