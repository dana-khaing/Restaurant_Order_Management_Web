"use client";

import { useEffect, useState } from "react";
import {
    CardTitle,
    CardDescription,
    CardHeader,
    CardContent,
    Card,
} from "@/components/ui/card";
import { Separator } from "@/components/ui/separator";
import { SERVICE_URLS } from "@/app/constants";
import clsx from "clsx";

export default function OrderConfirmPage({ params }) {
    const [order, setOrder] = useState(null);

    useEffect(() => {
        async function getOrder() {
            try {
                const response = await fetch(
                    `${SERVICE_URLS.ORDER_SERVICE}/orders/findOrder/${params.id}`,
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

                console.log(data);
                setOrder(data.order);
            } catch (error) {
                console.error(error);
            }
        }

        getOrder();
    }, []);

    return (
        <section className="p-6">
            <Card className="mx-auto max-w-3xl bg-white dark:bg-orange-500">
                <CardHeader>
                    <CardTitle className="text-orange-500 flex justify-between">
                        <span>Order Confirmation</span>
                        <span
                            className={clsx(
                                order?.orderStatus === "CANCELLED"
                                    ? "text-red-600"
                                    : "text-green-600"
                            )}
                        >
                            {order?.orderStatus}
                        </span>
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
                            <div className="ml-auto">{order?.creationDate}</div>
                        </div>
                    </div>
                    <Separator className="my-4" />
                    <div className="grid gap-2">
                        <div className="grid gap-2">
                            {order?.orderItems.map((item, index) => (
                                <div
                                    key={index}
                                    className="grid items-start gap-2"
                                >
                                    <img
                                        alt={item.name}
                                        className="aspect-square rounded-md object-cover"
                                        height="100"
                                        src={
                                            item.imageUrl || "/placeholder.svg"
                                        }
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
                            <div className="ml-auto">
                                £{order?.total.toFixed(2)}
                            </div>
                        </div>
                    </div>
                </CardContent>
                {order?.orderStatus === "DELIVERED" && (
                    <CardFooter>
                        <Button
                            onClick={() =>
                                router.push(`/customer/order/${params.id}/payment`)
                            }
                            variant="primary"
                        ></Button>
                    </CardFooter>
                )}
            </Card>
        </section>
    );
}
