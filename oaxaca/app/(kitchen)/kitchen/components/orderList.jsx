'use client';

export default function OrderList({ orders }) {
  return (
    <>
      {orders.map((order) => (
        <div className='grid grid-cols-5'>
          <div className=' h-[100%] w-[100%] border-2 border-[#786c6c] flex-col m-4 p-4 justify-center items-center'>
            <h1 className=' text-center'>Table {order.tableNumber}</h1>
            {order.orderItems.map((item) => (
              <div>
                <span>
                  {item.name} x {item.quantity}
                </span>
              </div>
            ))}
          </div>
        </div>
      ))}
    </>
  );
}
