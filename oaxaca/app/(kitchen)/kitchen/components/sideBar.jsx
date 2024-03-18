import { LogOut } from "lucide-react";

const siderItem = [
  {
    id: 1,
    item: "Order List",
    click: "order_list",
  },
  { id: 2, item: "Completed Order", click: "completed_order" },
];

export default function SidePanel() {
  return (
    <div className=" flex-col justify-center items-center text-center text-[#5D5252] h-[100%] ">
      <div className="flex-1">
        <h1 className=" font-bold text-lg mb-5"> DashBoard</h1>

        <div className="px-x3">
          {siderItem.map((item) => (
            <ul key={item.id}>
              <button
                className="m-3"
                //  onClick={() => onItemClick(item.click)}
              >
                {item.item}
              </button>
            </ul>
          ))}
        </div>
      </div>
      <div className="flex p-3">
        <button className="flex gap-3 p-5">
          <span>
            <LogOut />
          </span>{" "}
          <span>Logout</span>
        </button>
      </div>
    </div>
  );
}
