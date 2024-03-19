"use client";

import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";

const formSchema = z.object({
    customerName: z.string(),
    tableNumber: z.number(),
    orderType: z.enum(["Dine In", "Delivery"]),
});
import { Button } from "@/components/ui/button";
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

import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select";

export default function OrderPage() {
    // 1. Define your form.
    const form =
        useForm <
        z.infer <
        typeof formSchema >>
            {
                resolver: zodResolver(formSchema),
                defaultValues: {
                    username: "",
                },
            };

    // 2. Define a submit handler.
    function onSubmit(values) {
        // Do something with the form values.
        // ✅ This will be type-safe and validated.
        console.log(values);
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                <FormField
                    control={form.control}
                    name="customerName"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Name</FormLabel>
                            <FormControl>
                                <Input placeholder="John Smith" {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="tableNumber"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Table Number</FormLabel>
                            <FormControl>
                                <Input placeholder="15" {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="orderType"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Order Type</FormLabel>
                            <Select {...field}>
                                <SelectTrigger>
                                    <SelectValue />
                                </SelectTrigger>
                                <SelectContent>
                                    <SelectItem value="Dine In">
                                        Dine In
                                    </SelectItem>
                                    <SelectItem value="Delivery">
                                        Delivery
                                    </SelectItem>
                                </SelectContent>
                            </Select>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <Button type="submit">Order</Button>
            </form>
        </Form>
    );
}
