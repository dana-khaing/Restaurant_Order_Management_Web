'use client';

import { useEffect, useState } from 'react';
import SidePanel from './components/sideBar';
import OrderList from './components/orderList';

export default function KitchenHome() {
  const [orders, setOrders] = useState([]);

  async function fetchOrders() {
    try {
      const response = await fetch(`/api/order/fetchAll/`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
        cache: 'no-store',
      });

      if (!response.ok) {
        const errorText = await response.json();
        console.error(errorText);
        return;
      }

      const data = await response.json();
      const orders = data.orders;

      setOrders(orders.filter((order) => order.orderStatus === 'IN_PROGRESS'));
    } catch (error) {
      console.error(error);
    }
  }

  useEffect(() => {
    fetchOrders();
  }, []);

  useEffect(() => {
    const ws = new WebSocket('ws://localhost:8086/kitchen-orders');

    ws.onopen = () => {
      console.log('WebSocket is connected for kitchen orders');
    };

    ws.onmessage = (event) => {
      const newOrder = JSON.parse(event.data);
      let newOrders;

      switch (newOrder.orderStatus) {
        case 'PENDING':
        case 'IN_PROGRESS':
          console.log('inside in progress', newOrder);
          newOrders = [newOrder, ...orders];
          break;
        case 'PREPARED':
        case 'DELIVERED':
        case 'COMPLETED':
        default:
          newOrders = [...orders];
          break;
      }

      setOrders(newOrders);
    };

    ws.onerror = (error) => {
      console.log('WebSocket error: ', error);
    };

    ws.onclose = () => {
      console.log('WebSocket connection closed');
    };

    return () => {
      ws.close();
    };
  }, [orders]);

  return (
    <div className='my-0 flex justify-evenly w-screen'>
      <div className='w-[15%] my-2 ml-[-0.25rem] mr-5 h-[80vh] border-[#786c6c] border-2 px-5 py-5 flex-shrink-0'>
        <SidePanel />
      </div>
      <div className='flex-1'>
        <p className='py-5 mx-5 flex justify-center text-lg font-bold text-[#5D5252]'>
          Order List
        </p>
        <div>
          <OrderList orders={orders} setOrders={setOrders} />
        </div>
      </div>
    </div>
  );
}
