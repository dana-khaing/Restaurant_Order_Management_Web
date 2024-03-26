"use client";

import WaiterLoadingPage from "@/app/custom_components/waiter/waiter-loading-page";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function DashboardPage() {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        async function fetchOrders() {
            try {
                const response = await fetch(`/api/order/fetchAll/`, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    cache: "no-store",
                });

                if (!response.ok) {
                    const errorText = await response.json();
                    console.error(errorText);
                    return;
                }

                const data = await response.json();
                const orders = data.orders;

                setOrders(orders);
            } catch (error) {
                console.error(error);
            }
        }

        fetchOrders();
    }, []);

    useEffect(() => {
        const ws = new WebSocket("ws://localhost:8086/waiter-orders");

        ws.onopen = () => {
            console.log("WebSocket is connected");
        };

        ws.onmessage = (event) => {
            const newOrder = JSON.parse(event.data);
            let newOrders;
        
            switch (newOrder.orderStatus) {
                case "PENDING":
                    newOrders = [newOrder, ...orders];
                    break;
                case "IN_PROGRESS":
                case "PREPARED":
                case "DELIVERED":
                case "COMPLETED":
                    newOrders = orders.map((order) =>
                        order.id === newOrder.id ? newOrder : order
                    );
                    break;
                default:
                    newOrders = [...orders];
                    break;
            }
        
            setOrders(newOrders);
        };

        ws.onerror = (error) => {
            console.log("WebSocket error: ", error);
        };

        ws.onclose = () => {
            console.log("WebSocket connection closed");
        };

        return () => {
            ws.close();
        };
    }, []);

    if (orders.length === 0) return <WaiterLoadingPage />;

    const orderStatuses = {
        PENDING: { label: "Pending Orders", color: "bg-yellow-200" },
        IN_PROGRESS: { label: "In Progress", color: "bg-green-200" },
        PREPARED: { label: "Prepared Orders", color: "bg-orange-200" },
        DELIVERED: { label: "Delivered", color: "bg-red-200" },
        COMPLETED: { label: "Completed Orders", color: "bg-blue-200" },
    };

    const handleStatusChange = (id, newStatus) => {
        const updatedOrders = orders.map((order) =>
            order.id === id ? { ...order, orderStatus: newStatus } : order
        );
        setOrders(updatedOrders);
    };

    const handleDelete = (id) => {
        setOrders(orders.filter((order) => order.id !== id));
    };

    // Render action buttons based on order status
    const renderActions = (order) => {
        switch (order.orderStatus) {
            case "PENDING":
                return (
                    <button
                        className="text-sm font-medium underline text-yellow-700 mr-2"
                        onClick={() =>
                            handleStatusChange(order.id, "IN_PROGRESS")
                        }
                    >
                        Send to Kitchen
                    </button>
                );
            case "PREPARED":
                return (
                    <button
                        className="text-sm font-medium underline text-green-700 mr-2"
                        onClick={() =>
                            handleStatusChange(order.id, "DELIVERED")
                        }
                    >
                        Deliver Order
                    </button>
                );
            case "IN_PROGRESS":
            case "DELIVERED":
            case "COMPLETED":
                // No action besides delete for these statuses
                break;
            default:
                return null;
        }
    };

    return (
        <div className="grid grid-cols-1 p-4 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {Object.entries(orderStatuses).map(
                ([statusKey, { label, color }]) => {
                    const filteredOrders = orders.filter(
                        (order) => order.orderStatus === statusKey
                    );
                    return (
                        <div
                            key={statusKey}
                            className={`${color} p-4 rounded-lg`}
                        >
                            <h2 className="text-xl font-semibold mb-2">
                                {label}
                            </h2>
                            <p className="text-sm font-medium text-gray-500 mb-4">
                                {`${filteredOrders.length} ${
                                    filteredOrders.length > 1
                                        ? "orders"
                                        : "order"
                                }`}
                            </p>
                            <div className="grid grid-cols-1 gap-4">
                                {filteredOrders.map((order) => (
                                    <div key={order.id} className="space-y-4">
                                        <div className="flex items-center justify-between">
                                            <span className="text-sm font-semibold">{`Table ${order.tableNumber} - Order #${order.id}`}</span>
                                            <Link
                                                href="#"
                                                className="text-sm font-medium underline"
                                            >
                                                View
                                            </Link>
                                        </div>
                                        <div className="flex items-center justify-between">
                                            <span className="text-xs text-gray-500">{`${order.customerName} - ${order.orderItems.length} items`}</span>
                                            <div>
                                                {renderActions(order)}
                                                <button
                                                    className="text-sm font-medium underline text-red-700"
                                                    onClick={() =>
                                                        handleDelete(order.id)
                                                    }
                                                >
                                                    Delete
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    );
                }
            )}
        </div>
    );
}
