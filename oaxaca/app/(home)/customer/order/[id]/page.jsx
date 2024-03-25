import React from "react";
import {
    CardTitle,
    CardDescription,
    CardHeader,
    CardContent,
    CardFooter,
    Card,
} from "@/components/ui/card";
import { Separator } from "@/components/ui/separator";

async function getData(orderId) {
    try {
        const response = await fetch(
            `http://localhost:8085/orders/findOrder/${orderId}`,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            }
        );

        if (!response.ok) {
            const errorText = await response.json();
            console.error(errorText);
            return;
        }

        const data = await response.json();

        return data;
    } catch (error) {
        console.error(error);
    }
}

export default async function OrderConfirmPage({ params }) {
    const data = await getData(params.id);
    console.log("Data:", data);
    console.log("Order items:", data.order.orderItems)

    return (
        <section className="p-6">
            <Card className="mx-auto max-w-3xl bg-white dark:bg-orange-500">
                <CardHeader>
                    <CardTitle className="text-orange-500">
                        Order Confirmation
                    </CardTitle>
                    <CardDescription className="text-gray-500 dark:text-gray-400">
                        Thank you for your purchase! Your order is confirmed.
                    </CardDescription>
                </CardHeader>
                <CardContent>
                    <div className="grid gap-2 text-sm">
                        <div className="flex items-center">
                            <div className="font-medium">Order number:</div>
                            <div className="ml-auto">#{params.id}</div>
                        </div>
                        <div className="flex items-center">
                            <div className="font-medium">Date: </div>
                            <div className="ml-auto">
                                {data.order.creationDate}
                            </div>
                        </div>
                    </div>
                    <Separator className="my-4" />
                    <div className="grid gap-2">
                        <div className="grid gap-2">
                            {data.order.orderItems.map((item, index) => (
                                <div
                                    key={index}
                                    className="grid items-start gap-2"
                                >
                                    <img
                                        alt={item.name}
                                        className="aspect-square rounded-md object-cover"
                                        height="100"
                                        src={item.imageUrl || "/placeholder.svg"}
                                        width="100"
                                    />
                                    <div className="font-medium">
                                        {item.name}
                                    </div>
                                    <div className="text-sm text-gray-500 dark:text-gray-400">
                                        {item.description}
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                    <Separator className="my-4" />
                    <div className="grid gap-2">
                        <Separator className="my-2" />
                        <div className="flex items-center font-medium">
                            <div>Total</div>
                            <div className="ml-auto">£{data.order.total}</div>
                        </div>
                    </div>
                </CardContent>
                <CardFooter>
                    <OrderProgress currentStage="Prepared" />
                </CardFooter>
            </Card>
        </section>
    );
}

function OrderProgress({ currentStage }) {
    const stages = ['PENDING', 'IN PROGRESS'];

    return (
        <div className="grid gap-2">
            {stages.map((stage, index) => {
                let color;
                if (currentStage === stage) {
                    color = 'text-green-500'; // Current stage color
                } else if (stages.indexOf(currentStage) > index) {
                    color = 'text-blue-500'; // Completed stage color
                } else {
                    color = 'text-gray-500'; // Future stage color
                }

                return (
                    <div key={index} className={`text-sm ${color} font-bold`}>
                        {stage}
                    </div>
                );
            })}
        </div>
    );
}