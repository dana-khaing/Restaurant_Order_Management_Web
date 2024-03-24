"use client";

import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { fetchCart } from "@/app/actions/cart";
import { useRouter } from "next/navigation";
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
import React from "react";

import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select";

export default function OrderPage() {
    const formSchema = z.object({
        customerName: z.string(),
        tableNumber: z.string(),
        orderType: z.enum(["Dine In", "Delivery"]),
    });

    // 1. Define your form.
    const form = useForm({
        resolver: zodResolver(formSchema),
        defaultValues: {
            customerName: "",
            tableNumber: "",
            orderType: "Dine In",
        },
    });

    const router = useRouter();

    function convertOrderType(orderTypeString) {
        let orderType;
        switch (orderTypeString) {
            case "Dine In":
                orderType = "DINE_IN";
                break;
            case "Delivery":
                orderType = "DELIVERY";
                break;
            case "Takeaway":
                orderType = "TAKEAWAY";
                break;
            default:
                break;
        }

        return orderType;
    }

    async function onSubmit(values) {
        try {
            const orderType = convertOrderType(values.orderType);
            const processedValues = {
                ...values,
                orderType: orderType,
            };
            const cart = await fetchCart();
            const order = {
                ...processedValues,
                cart: cart,
            };
            console.log(order);

            const response = await fetch("/api/order/place", {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "POST",
                body: JSON.stringify(order),
            });

            if (!response.ok) {
                const errorText = await response.json();
                console.error(errorText);
                return;
            }
            const data = await response.json();
            console.log("Response:", data);

            router.push(`/customer/order/${data.order.id}`);
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <section className="p-8 flex flex-col justify-center  gap-10">
            <Form {...form}>
                <form
                    onSubmit={form.handleSubmit(onSubmit)}
                    className="space-y-8"
                >
                    <FormField
                        control={form.control}
                        name="customerName"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Name</FormLabel>
                                <FormControl>
                                    <Input
                                        placeholder="John Smith"
                                        {...field}
                                    />
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
        </section>
    );
}
