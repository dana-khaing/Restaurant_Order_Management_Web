
import { CardTitle, CardDescription, CardHeader, CardContent, CardFooter, Card } from "@/components/ui/card"
import { Separator } from "@/components/ui/separator"
import OrderContext from "@/app/custom_components/context/OrderContext";

export default function OrderConfirmPage({params}) {
    const {order} = React.useContext(OrderContext);
  return (
    <Card className="mx-auto max-w-3xl bg-white dark:bg-orange-500">
      <CardHeader>
        <CardTitle className="text-orange-500">Order Confirmation</CardTitle>
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
            <div className="font-medium">Date:</div>
            <div className="ml-auto">March 24, 2024</div>
          </div>
        </div>
        <Separator className="my-4" />
        <div className="grid gap-2">
          <div className="grid items-start gap-2">
            <img
              alt="Product image"
              className="aspect-square rounded-md object-cover"
              height="100"
              src="/placeholder.svg"
              width="100"
            />
            <div className="font-medium">Glimmer Lamps</div>
            <div className="text-sm text-gray-500 dark:text-gray-400">
              Beautiful lamps that add a warm glow to your home.
            </div>
          </div>
          <div className="grid items-start gap-2">
            <img
              alt="Product image"
              className="aspect-square rounded-md object-cover"
              height="100"
              src="/placeholder.svg"
              width="100"
            />
            <div className="font-medium">Aqua Filters</div>
            <div className="text-sm text-gray-500 dark:text-gray-400">
              High-quality water filters that provide clean and fresh drinking water.
            </div>
          </div>
        </div>
        <Separator className="my-4" />
        <div className="grid gap-2">
          <div className="flex items-center">
            <div className="font-medium">Subtotal</div>
            <div className="ml-auto">$169.00</div>
          </div>
          <div className="flex items-center">
            <div className="font-medium">Discount</div>
            <div className="ml-auto">-$19.00</div>
          </div>
          <Separator className="my-2" />
          <div className="flex items-center font-medium">
            <div>Total</div>
            <div className="ml-auto">$150.00</div>
          </div>
        </div>
      </CardContent>
      <CardFooter>
        <div className="grid gap-2">
          <div className="text-sm">Your order will go through the following stages:</div>
          <div>
            Confirmed
            <br />
            Prepared
            <br />
            Delivered
          </div>
        </div>
      </CardFooter>
    </Card>
  )
}

