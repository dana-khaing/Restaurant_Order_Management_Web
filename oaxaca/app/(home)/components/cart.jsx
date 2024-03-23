"use client";

import { removeCartItem, updateCartItem } from "@/app/actions/cart";
import { Button } from "@/components/ui/button";
import {
    Popover,
    PopoverContent,
    PopoverTrigger,
} from "@/components/ui/popover";
import { ShoppingCart } from "lucide-react";
import { useRouter } from "next/navigation";



export default function Cart({ cartItems }) {
    const router = useRouter();

    const decrementQuantity = async (productId) => {
        const item = cartItems.find((item) => item.productId === productId);
        if (item.quantity > 1) {
            await updateCartItem(productId, item.quantity - 1);
        } else {
            await removeCartItem(productId);
        }
    };

    const incrementQuantity = async (productId) => {
        const item = cartItems.find((item) => item.productId === productId);
        await updateCartItem(productId, item.quantity + 1);
    };

    const disabled = cartItems?.length === 0;

    const goToConfirmOrder = () => {
        router.push("/customer/order");
    };

    return (
        <Popover>
            <PopoverTrigger asChild>
                <button variant="outline" className="relative mr-3">
                    <span className="absolute -right-2 -top-2 inline-block h-5 w-5 rounded-full bg-gray-300 text-center text-xs font-medium leading-5 text-black">
                        {cartItems?.length || 0}
                    </span>
                    <ShoppingCart className="" size={"24px"} />
                </button>
            </PopoverTrigger>
            <PopoverContent className="w-96">
                <div className="px-2">
                    <h3 className="text-xl font-semibold mb-2">Your Cart</h3>
                    <hr />
                    <div className="flex flex-col gap-4 mt-2">
                        {cartItems?.map((item) => (
                            <div key={item.id} className="flex ">
                                <img
                                    className="w-16 h-16 rounded-lg"
                                    src="/images/burrito.jpeg"
                                />
                                <div className="flex justify-between w-full pl-2">
                                    <div className="flex flex-col gap-1">
                                        <span className="font-semibold">
                                            {item.productName}
                                        </span>
                                        <div className="flex gap-2 items-center">
                                            <span
                                                onClick={() =>
                                                    decrementQuantity(
                                                        item.productId
                                                    )
                                                }
                                                className="bg-gray-500 rounded-lg px-2 py-0.5 text-white cursor-pointer"
                                            >
                                                -
                                            </span>
                                            <span>{item.quantity}</span>
                                            <span
                                                onClick={() =>
                                                    incrementQuantity(
                                                        item.productId
                                                    )
                                                }
                                                className="bg-gray-500 rounded-lg px-2 py-0.5 text-white cursor-pointer"
                                            >
                                                +
                                            </span>
                                        </div>
                                    </div>
                                    <span className="ml-2 text-gray-700">
                                        £{item.price}
                                    </span>
                                </div>
                            </div>
                        ))}
                        <span className="text-right text-lg font-semibold">
                            Total:{" "}
                            {Number(
                                cartItems?.reduce(
                                    (prev, item) =>
                                        prev + item.quantity * item.price,
                                    0
                                )
                            )?.toFixed(2) || 0}
                        </span>
                    </div>
                    <Button
                        className="w-full mt-4"
                        onClick={goToConfirmOrder}
                        disabled={disabled}
                    >
                        Checkout
                    </Button>
                </div>
            </PopoverContent>
        </Popover>
    );
}
