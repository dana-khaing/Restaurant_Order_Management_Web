'use client';

import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
const formSchema = z.object({
  customerName: z.string(),
  tableNumber: z.string(),
  orderType: z.enum(['Dine In', 'Delivery']),
});
import { fetchCart } from '@/app/actions/cart';
import { useRouter } from 'next/navigation';
import { Button } from '@/components/ui/button';
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form';
import { Input } from '@/components/ui/input';

import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';

export default function OrderPage() {
  // 1. Define your form.
  const form = useForm({
    resolver: zodResolver(formSchema),
    defaultValues: {
      customerName: '',
      tableNumber: '',
      orderType: 'Dine In',
    },
  });

  const router = useRouter();

  async function onSubmit(values) {
    try {
      const cart = await fetchCart();

      const orderTypeMap = {
        'Dine In': 'DINE_IN',
        Delivery: 'DELIVERY',
        'Take Out': 'TAKE_OUT',
      };

      const order = {
        ...values,
        orderType: orderTypeMap[values.orderType],
        cart: cart,
      };

      console.log('Order:', order);

      const response = await fetch('/api/order/place', {
        headers: {
          'Content-Type': 'application/json',
        },
        method: 'POST',
        body: JSON.stringify(order),
      });

      if (!response.ok) {
        const errorText = await response.json();
        console.error(errorText);
        return;
      }
      const data = await response.json();
      console.log(data);
      console.log('Order ID:', data.order.id);

      router.push(`/customer/order/${data.order.id}`);
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <section className='p-8 flex flex-col justify-center  gap-10'>
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className='space-y-8'>
          <FormField
            control={form.control}
            name='customerName'
            render={({ field }) => (
              <FormItem>
                <FormLabel>Name</FormLabel>
                <FormControl>
                  <Input placeholder='John Smith' {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name='tableNumber'
            render={({ field }) => (
              <FormItem>
                <FormLabel>Table Number</FormLabel>
                <FormControl>
                  <Input placeholder='15' {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name='orderType'
            render={({ field }) => (
              <FormItem>
                <FormLabel>Order Type</FormLabel>
                <Select {...field}>
                  <SelectTrigger>
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value='Dine In'>Dine In</SelectItem>
                    <SelectItem value='Delivery'>Delivery</SelectItem>
                  </SelectContent>
                </Select>
                <FormMessage />
              </FormItem>
            )}
          />

          <Button type='submit'>Order</Button>
        </form>
      </Form>
    </section>
  );
}
