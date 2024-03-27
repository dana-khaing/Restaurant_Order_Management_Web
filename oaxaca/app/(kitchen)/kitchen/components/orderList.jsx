'use client';

export default function OrderList({ orders, setOrders }) {
  const handlePreparedOrder = async (id) => {
    const response = await fetch(`/api/order/preparedOrder`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ id }),
    });

    if (!response.ok) {
      const errorText = await response.json();
      console.error(errorText);
      return;
    }

    const data = await response.json();
    console.log(data);
    setOrders(orders.filter((order) => order.id !== id));
  };
  return (
    <>
      <div className='grid grid-cols-3 gap-2'>
        {orders.map((order) => (
          <div className=' h-[100%] w-[100%] border-2 border-[#786c6c] flex-col m-4 p-4 justify-center items-center'>
            <h1 className=' text-center'>Table {order.tableNumber}</h1>
            {order.orderItems.map((item) => (
              <div>
                <span>
                  {item.name} x {item.quantity}
                </span>
                <button onClick={() => handlePreparedOrder(order.id)}>
                  Done
                </button>
              </div>
            ))}
          </div>
        ))}
      </div>
    </>
  );
}
