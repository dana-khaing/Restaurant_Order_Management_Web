'use client';

import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form';
import { Input } from '@/components/ui/input';
import { useToast } from '@/components/ui/use-toast';
import { Button } from '@/components/ui/button';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import AuthHeader from '../../components/AuthHeader';

export default function WaiterLoginPage() {
  const router = useRouter();
  const { toast } = useToast();

  const form = useForm({
    defaultValues: {
      username: '',
      password: '',
      email: '',
      name: '',
    },
    resolver: zodResolver(
      z.object({
        username: z
          .string({
            required_error: 'Username should be at least 4 characters',
          })
          .min(4)
          .max(16),
        password: z
          .string({
            required_error: 'Password has to be a minimum of 8 characters',
          })
          .min(8)
          .max(32),
        email: z.string({ required_error: 'You need a valid email' }).max(320),
        name: z.string().min(6).max(60),
      })
    ),
    mode: 'onBlur',
  });

  async function onSubmit(values) {
    console.log(values);

    try {
      const response = await fetch('/api/auth/customer/signup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(values),
      });

      router.push('/customer/login');

      if (!response.ok) {
        throw new Error(response.error);
      }

      console.log(data);
    } catch (error) {}

    toast({
      title: 'You submitted the following values:',
      description: 'Signed up successfully',
    });
  }

  return (
    <>
      <AuthHeader text={'Signup'} />

      <Form {...form}>
        <form
          onSubmit={form.handleSubmit(onSubmit)}
          className='space-y-8 w-full text-start'
        >
          <FormField
            control={form.control}
            name='email'
            render={({ field }) => (
              <FormItem>
                <FormLabel>Email</FormLabel>
                <FormControl>
                  <Input placeholder='shadcn@shadcn.com' {...field} />
                </FormControl>
                <FormDescription>
                  This is your public display email.
                </FormDescription>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name='username'
            render={({ field }) => (
              <FormItem>
                <FormLabel>Username</FormLabel>
                <FormControl>
                  <Input placeholder='shadcn' {...field} />
                </FormControl>
                <FormDescription>
                  This is your public display name.
                </FormDescription>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name='password'
            render={({ field }) => (
              <FormItem>
                <FormLabel>Password</FormLabel>
                <FormControl>
                  <Input placeholder='shadcn12345' type='password' {...field} />
                </FormControl>
                <FormDescription>
                  This is your password you used to create your account.
                </FormDescription>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name='name'
            render={({ field }) => (
              <FormItem>
                <FormLabel> Full Name </FormLabel>
                <FormControl>
                  <Input placeholder='shadcn' type='firstName' {...field} />
                </FormControl>

                <FormMessage />
              </FormItem>
            )}
          />

          <div className='flex justify-center gap-4 items-center w-full flex-col'>
            <Button type='submit' className='w-full'>
              <p>Signup</p>
            </Button>

            <Link href={'/customer/login'} className='cursor-pointer'>
              <FormLabel>
                {' '}
                Have have an account?{' '}
                <span className='text-red-500'> Login. </span>{' '}
              </FormLabel>
            </Link>
          </div>
        </form>
      </Form>
    </>
  );
}
