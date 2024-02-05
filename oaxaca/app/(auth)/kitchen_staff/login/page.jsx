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
import { Checkbox } from '@/components/ui/checkbox';
import { useToast } from '@/components/ui/use-toast';
import { Button } from '@/components/ui/button';
import Link from 'next/link';
import SocialLinks from '@/app/(auth)/components/SocialLinks';
import AuthHeader from '@/app/(auth)/components/AuthHeader';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';

import { CalendarIcon } from '@radix-ui/react-icons';
import { format } from 'date-fns';
import { cn } from '@/lib/utils';
import { Calendar } from '@/components/ui/calendar';
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover';

export default function KitchenStaffLoginPage() {
  const form = useForm({
    defaultValues: {
      username: '',
      password: '',
      remember_me: false,
      firstName: '',
      lastName: '',
      role: 'Chef',
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
        firstName: z
          .string({ required_error: 'First Name Minimum 4 characters' })
          .min(4)
          .max(32),
        lastName: z
          .string({ required_error: 'Last Name Minimum 4 characters' })
          .min(4)
          .max(32),
        role: z.enum(['Chef', 'Sous Chef', 'Helper']),
        remember_me: z.boolean().optional(),
      })
    ),
    mode: 'onBlur',
  });

  function onSubmit(values) {
    // Do something with the form values.
    console.log(values);
    toast({
      title: 'You submitted the following values:',
      description: (
        <pre className='mt-2 w-[340px] rounded-md bg-slate-950 p-4'>
          <code className='text-white'>{JSON.stringify(values, null, 2)}</code>
        </pre>
      ),
    });
  }

  const { toast } = useToast();

  return (
    <div>
      <AuthHeader text={'Login'} />

      <Form {...form}>
        <form
          onSubmit={form.handleSubmit(onSubmit)}
          className='space-y-8 w-full text-start'
        >
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
                  <Input placeholder='shadcn' type='password' {...field} />
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
            name='firstName'
            render={({ field }) => (
              <FormItem>
                <FormLabel> First Name </FormLabel>
                <FormControl>
                  <Input placeholder='shadcn' type='firstName' {...field} />
                </FormControl>

                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name='lastName'
            render={({ field }) => (
              <FormItem>
                <FormLabel> Last Name </FormLabel>
                <FormControl>
                  <Input placeholder='shadcn' type='firstName' {...field} />
                </FormControl>

                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name='role'
            render={({ field }) => (
              <FormItem>
                <FormLabel>Role</FormLabel>
                <Select
                  onValueChange={field.onChange}
                  defaultValue={field.value}
                >
                  <FormControl>
                    <SelectTrigger>
                      <SelectValue placeholder='Select a verified email to display' />
                    </SelectTrigger>
                  </FormControl>
                  <SelectContent>
                    <SelectItem value='Chef'>Chef</SelectItem>
                    <SelectItem value='Sous Chef'>Sous Chef</SelectItem>
                    <SelectItem value='Helper'>Helper</SelectItem>
                  </SelectContent>
                </Select>
                <FormDescription>
                  This is the role you serve in our kitchen.
                </FormDescription>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name='remember_me'
            render={({ field }) => (
              <FormItem className='flex flex-row items-center w-full justify-between space-x-3 space-y-0 rounded-md border p-4 shadow'>
                <div className='flex justify-center items-center gap-2'>
                  <FormControl>
                    <Checkbox
                      checked={field.value}
                      onCheckedChange={field.onChange}
                    />
                  </FormControl>
                  <div className='space-y-1 leading-none'>
                    <FormLabel>Remember me</FormLabel>
                  </div>
                </div>

                <div>
                  <FormLabel className='text-red-500'>
                    Forgot your password?
                  </FormLabel>
                </div>
              </FormItem>
            )}
          />

          <div className='flex justify-center gap-4 items-center w-full flex-col'>
            <Button type='submit' className='w-full'>
              <p>Login</p>
            </Button>

            <Link href={'customer/signup'} className='cursor-pointer'>
              <FormLabel>
                {' '}
                Don't have an account?{' '}
                <span className='text-red-500'> Signup. </span>{' '}
              </FormLabel>
            </Link>
          </div>

          <SocialLinks />
        </form>
      </Form>
    </div>
  );
}
