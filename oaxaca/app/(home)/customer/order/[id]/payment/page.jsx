
import { CardTitle, CardDescription, CardHeader, CardContent, CardFooter, Card } from "@/components/ui/card"
import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"
import { SelectValue, SelectTrigger, SelectItem, SelectContent, Select } from "@/components/ui/select"
import { Checkbox } from "@/components/ui/checkbox"
import { Button } from "@/components/ui/button"

async function payForOrder (orderId){
    const response = await fetch(
        `${SERVICE_URLS.ORDER_SERVICE}/orders/payOrder/${orderId}`,
        {
            method: "POST",
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
    console.log(data)
}

export default function PaymentPage({params}) {
    const orderId = params.id
    console.log(orderId);
    



  return (
    <Card className="w-full max-w-3xl p-0">
      <CardHeader className="bg-white pb-4">
        <div className="space-y-1.5">
          <CardTitle className="text-2xl font-bold">Order Payment for Oaxaca</CardTitle>
          <CardDescription>Please enter your payment information</CardDescription>
        </div>
      </CardHeader>
      <CardContent className="bg-white space-y-4 py-4">
        <div className="space-y-2">
          <Label htmlFor="name">Name</Label>
          <Input id="name" placeholder="Enter your name" />
        </div>
        <div className="space-y-2">
          <Label htmlFor="number">Card number</Label>
          <Input id="number" placeholder="Enter your email" type="number" />
        </div>
        <div className="grid grid-cols-3 gap-4">
          <div className="space-y-2">
            <Label htmlFor="month">Expires</Label>
            <Select>
              <SelectTrigger aria-label="Month" id="month">
                <SelectValue placeholder="Month" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="1">January</SelectItem>
                <SelectItem value="2">February</SelectItem>
                <SelectItem value="3">March</SelectItem>
                <SelectItem value="4">April</SelectItem>
                <SelectItem value="5">May</SelectItem>
                <SelectItem value="6">June</SelectItem>
                <SelectItem value="7">July</SelectItem>
                <SelectItem value="8">August</SelectItem>
                <SelectItem value="9">September</SelectItem>
                <SelectItem value="10">October</SelectItem>
                <SelectItem value="11">November</SelectItem>
                <SelectItem value="12">December</SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div className="space-y-2">
            <Label htmlFor="year">Year</Label>
            <Select>
              <SelectTrigger aria-label="Year" id="year">
                <SelectValue placeholder="Year" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="2023">2023</SelectItem>
                <SelectItem value="2024">2024</SelectItem>
                <SelectItem value="2025">2025</SelectItem>
                <SelectItem value="2026">2026</SelectItem>
                <SelectItem value="2027">2027</SelectItem>
                <SelectItem value="2028">2028</SelectItem>
                <SelectItem value="2029">2029</SelectItem>
                <SelectItem value="2030">2030</SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div className="space-y-2">
            <Label htmlFor="cvc">CVC</Label>
            <Input id="cvc" placeholder="CVC" />
          </div>
        </div>
        <div className="flex items-center space-x-2">
          <Checkbox id="save" />
          <Label className="text-sm leading-none" htmlFor="save">
            Save payment information for future orders
          </Label>
        </div>
      </CardContent>
      <CardFooter className="bg-white space-y-4 pt-4">
        <Button className="w-full" size="lg" type="submit" onClick={payForOrder}>
          Pay Now
        </Button>
        <Button className="w-full" size="lg" variant="outline" onClick={() => router.push(`/customer/order/${params.id}`)}>
          Cancel
        </Button>
      </CardFooter>
    </Card>
  )
}

