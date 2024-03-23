'use client';

import Link from 'next/link';
import { useState } from 'react';

export default function DashboardPage() {
  const [orders, setOrders] = useState([
    {
      id: 1,
      table: 4,
      name: 'Juan Perez',
      time: '12:34 PM',
      order: 34562,
      items: 2,
      status: 'pending',
      tableNumber: 4,
    },
    {
      id: 2,
      table: 7,
      name: 'Maria Garcia',
      time: '12:45 PM',
      order: 34563,
      items: 1,
      status: 'in-progress',
      tableNumber: 7,
    },
    {
      id: 3,
      table: 2,
      name: 'Carlos Hernandez',
      time: '1:12 PM',
      order: 34564,
      items: 3,
      status: 'delivered',
      tableNumber: 2,
    },
    {
      id: 4,
      table: 5,
      name: 'Luisa Martinez',
      time: '1:34 PM',
      order: 34565,
      items: 2,
      status: 'completed',
      tableNumber: 5,
    },
  ]);

  const pendingOrders = orders.filter((order) => order.status === 'pending');
  const inProgressOrders = orders.filter(
    (order) => order.status === 'in-progress'
  );
  const deliveredOrders = orders.filter(
    (order) => order.status === 'delivered'
  );
  const completedOrders = orders.filter(
    (order) => order.status === 'completed'
  );

  const handleDelete = (id) => {
    setOrders(orders.filter((order) => order.id !== id));
  };

  const sendOrderToKitchen = (id) => {
    const order = orders.find((order) => order.id === id);
    order.status = 'in-progress';
    setOrders([...orders]);
  };

  const handleDeliverOrder = (id) => {
    const order = orders.find((order) => order.id === id);
    order.status = 'delivered';
    setOrders([...orders]);
  };

  const handleCompleteOrder = (id) => {
    const order = orders.find((order) => order.id === id);
    order.status = 'completed';
    setOrders([...orders]);
  };

  return (
    <div className='grid grid-cols-1 p-4 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 gap-6'>
      <div className='bg-yellow-200 p-4 rounded-lg'>
        <h2 className='text-xl font-semibold mb-2'>Pending Orders</h2>
        <p className='text-sm font-medium text-gray-500 mb-4'>
          {`${pendingOrders.length} ${
            pendingOrders.length > 1 ? 'orders' : 'order'
          } pending`}
        </p>
        <div className='grid grid-cols-1 gap-4'>
          {pendingOrders.map((order) => (
            <div className='space-y-4' key={order.id}>
              <div className='flex items-center space-x-4'>
                <div className='flex items-center space-x-4'>
                  <img
                    alt='Image'
                    className='rounded-full'
                    height='40'
                    src={order.imageSrc}
                    style={{
                      aspectRatio: '40/40',
                      objectFit: 'cover',
                    }}
                    width='40'
                  />
                  <div className='flex flex-col'>
                    <span className='text-sm font-semibold'>
                      {`Table ${order.tableNumber}`}
                    </span>
                    <span className='text-xs text-gray-500'>
                      {order.customerName}
                    </span>
                  </div>
                  <Link
                    className='ml-auto text-sm font-medium underline'
                    href='#'
                  >
                    View
                  </Link>
                  <button
                    className='text-sm font-medium underline text-yellow-700'
                    onClick={() => sendOrderToKitchen(order.id)}
                  >
                    Send to Kitchen
                  </button>
                </div>
              </div>
              <div className='flex items-center space-x-4'>
                <span className='text-sm font-semibold'>{order.time}</span>
                <span className='text-sm font-semibold'>Order #{order.id}</span>
                <span className='text-sm font-semibold'>
                  {order.items} items
                </span>
                <button
                  className='text-sm font-medium underline text-red-700'
                  onClick={() => handleDelete(order.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className='bg-green-200 p-4 rounded-lg'>
        <h2 className='text-xl font-semibold mb-2'>In Progress</h2>
        <p className='text-sm font-medium text-gray-500 mb-4'>
          {`${inProgressOrders.length} ${
            inProgressOrders.length > 1 ? 'orders' : 'order'
          } being prepared`}
        </p>
        <div className='grid grid-cols-1 gap-4'>
          {inProgressOrders.map((order) => (
            <div key={order.id} className='space-y-4'>
              <div className='flex items-center text-center space-x-4'>
                <div className='flex items-center space-x-4'>
                  <img
                    alt='Image'
                    className='rounded-full'
                    height='40'
                    src={order.imageSrc}
                    style={{
                      aspectRatio: '40/40',
                      objectFit: 'cover',
                    }}
                    width='40'
                  />
                  <div className='flex flex-col'>
                    <span className='text-sm font-semibold'>
                      {`Table ${order.tableNumber}`}
                    </span>
                    <span className='text-xs text-gray-500'>
                      {order.customerName}
                    </span>
                  </div>
                  <Link
                    className='ml-auto text-sm font-medium underline'
                    href='#'
                  >
                    View
                  </Link>
                  <button
                    className='text-sm font-medium underline text-yellow-700'
                    onClick={() => handleDeliverOrder(order.id)}
                  >
                    Deliver order
                  </button>
                </div>
              </div>
              <div className='flex items-center space-x-4'>
                <span className='text-sm font-semibold'>{order.time}</span>
                <span className='text-sm font-semibold'>Order #{order.id}</span>
                <span className='text-sm font-semibold'>
                  {order.items} items
                </span>
                <button
                  className='text-sm font-medium underline text-red-700'
                  onClick={() => handleDelete(order.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className='bg-red-200 p-4 rounded-lg'>
        <h2 className='text-xl font-semibold mb-2'>Delivered</h2>
        <p className='text-sm font-medium text-gray-500 mb-4'>
          {`${deliveredOrders.length} ${
            deliveredOrders.length > 1 ? 'orders' : 'order'
          } delivered`}
        </p>
        <div className='grid grid-cols-1 gap-4'>
          {deliveredOrders.map((order) => (
            <div className='space-y-4' key={order.id}>
              <div className='flex items-center space-x-4'>
                <div className='flex items-center space-x-4'>
                  <img
                    alt='Image'
                    className='rounded-full'
                    height='40'
                    src={order.imageSrc}
                    style={{
                      aspectRatio: '40/40',
                      objectFit: 'cover',
                    }}
                    width='40'
                  />
                  <div className='flex flex-col'>
                    <span className='text-sm font-semibold'>
                      {`Table ${order.tableNumber}`}
                    </span>
                    <span className='text-xs text-gray-500'>
                      {order.customerName}
                    </span>
                  </div>
                  <Link
                    className='ml-auto text-sm font-medium underline'
                    href='#'
                  >
                    View
                  </Link>
                  <button
                    className='text-sm font-medium underline text-yellow-700'
                    onClick={() => handleCompleteOrder(order.id)}
                  >
                    Complete order
                  </button>
                </div>
              </div>
              <div className='flex items-center space-x-4'>
                <span className='text-sm font-semibold'>{order.time}</span>
                <span className='text-sm font-semibold'>Order #{order.id}</span>
                <span className='text-sm font-semibold'>
                  {order.items} items
                </span>
                <button
                  className='text-sm font-medium underline text-red-700'
                  onClick={() => handleDelete(order.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className='bg-blue-200 p-4 rounded-lg'>
        <h2 className='text-xl font-semibold mb-2'>Completed Orders</h2>
        <p className='text-sm font-medium text-gray-500 mb-4'>
          {`${completedOrders.length} ${
            completedOrders.length > 1 ? 'orders' : 'order'
          } completed`}
        </p>
        <div className='grid grid-cols-1 gap-4'>
          {completedOrders.map((order) => (
            <div className='space-y-4' key={order.id}>
              <div className='flex items-center space-x-4'>
                <div className='flex items-center space-x-4'>
                  <img
                    alt='Image'
                    className='rounded-full'
                    height='40'
                    src={order.imageSrc}
                    style={{
                      aspectRatio: '40/40',
                      objectFit: 'cover',
                    }}
                    width='40'
                  />
                  <div className='flex flex-col'>
                    <span className='text-sm font-semibold'>
                      {`Table ${order.tableNumber}`}
                    </span>
                    <span className='text-xs text-gray-500'>
                      {order.customerName}
                    </span>
                  </div>
                  <Link
                    className='ml-auto text-sm font-medium underline'
                    href='#'
                  >
                    View
                  </Link>
                </div>
              </div>
              <div className='flex items-center space-x-4'>
                <span className='text-sm font-semibold'>{order.time}</span>
                <span className='text-sm font-semibold'>Order #{order.id}</span>
                <span className='text-sm font-semibold'>
                  {order.items} items
                </span>
                <button
                  className='text-sm font-medium underline text-red-700'
                  onClick={() => handleDelete(order.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
