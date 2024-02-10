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
import AuthNav from "@/app/custom_components/auth/AuthNav";
import AuthHeader from "@/app/custom_components/auth/AuthHeader";
import AuthBanner from "@/app/custom_components/auth/AuthBanner";

import { useRouter } from "next/navigation";
import { useEffect } from "react";

export default function WaiterLoginPage() {
	const router = useRouter();

	const form = useForm({
		defaultValues: {
			username: "",
			password: "",
			rememberMe: false,
			firstName: "",
			lastName: "",
		},
		resolver: zodResolver(
			z.object({
				username: z.string({ required_error: "Username should be at least 4 characters" }).min(4).max(16),
				password: z.string({ required_error: "Password has to be a minimum of 8 characters" }).min(8).max(32),
				firstName: z.string({ required_error: "First Name Minimum 4 characters" }).min(1).max(32),
				lastName: z.string({ required_error: "Last Name Minimum 4 characters" }).min(1).max(32),
				rememberMe: z.boolean().optional(),
			})
		),
		mode: "onBlur",
	});

	useEffect(() => {

		async function validateRememberMeToken() {

			try {

				const response = await fetch("/api/auth/waiter/login/validate", {

					method: "GET",
					headers: { "Content-Type": "application/json" },
				});

				// First, check if the response is OK
				if (!response.ok) {
					const errorText = await response.text(); // Get the error message as text
					console.error("Error:", errorText);
					toast({
						title: "Sign up failed.",
						description: "Please try again.",
					});
					return;
				}

				// Then, safely parse the JSON
				const data = await response.json();
				console.log("Success:", data);
				toast({
					title: "Logged in successfully",
					description: "Redirecting to home page.",
				});
				router.push("/waiter/home");






			} catch (error) {

				console.error("An error occurred:", error);
				toast({
					title: "Sign up failed.",
					description: "Please try again.",
				});
			}

		}


		validateRememberMeToken();


	}, [])



	async function onSubmit(values) {

		const endpoint = !values.rememberMe ? "/api/auth/waiter/login" : "/api/auth/waiter/login/remember-me";

		try {
			const response = await fetch(endpoint, {
				method: "POST",
				headers: { "Content-Type": "application/json" },
				body: JSON.stringify(values),
			});

			// First, check if the response is OK
			if (!response.ok) {
				const errorText = await response.text(); // Get the error message as text
				console.error("Error:", errorText);
				toast({
					title: "Sign up failed.",
					description: "Please try again.",
				});
				return;
			}

			// Then, safely parse the JSON
			const data = await response.json();
			console.log("Success:", data);
			toast({
				title: "Logged in successfully",
				description: "Redirecting to home page.",
			});
			//	router.push("/waiter/home");


		} catch (error) {
			console.error("An error occurred:", error);
			toast({
				title: "Sign up failed.",
				description: "Please try again.",
			});
		}
	}


	const { toast } = useToast();

	return (
		<main className="flex flex-col justify-center w-full items-center gap-6 lg:flex-row">
			<section className="flex flex-col justify-center items-center w-full h-full gap-8 text-center lg:items-start p-8">
				<AuthNav />

				<AuthHeader text={"Login"} />

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
							name="firstName"
							render={({ field }) => (
								<FormItem>
									<FormLabel> First Name </FormLabel>
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

						<FormField
							control={form.control}
							name="lastName"
							render={({ field }) => (
								<FormItem>
									<FormLabel> Last Name </FormLabel>
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

						<FormField
							control={form.control}
							name="rememberMe"
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


						</div>

					</form>
				</Form>

				<Toaster />
			</section>

			<AuthBanner />
		</main>
	);
}

