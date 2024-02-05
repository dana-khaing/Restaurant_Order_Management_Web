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
import { Toaster } from '@/components/ui/toaster';
import { useToast } from '@/components/ui/use-toast';
import { Button } from '@/components/ui/button';
import Link from 'next/link';
import SocialLinks from '@/app/(auth)/components/SocialLinks';
import AuthNav from '@/app/(auth)/components/AuthNav';
import AuthHeader from '@/app/(auth)/components/AuthHeader';
import AuthBanner from '@/app/(auth)/components/AuthBanner';
import { CalendarIcon } from '@radix-ui/react-icons';
import { format } from 'date-fns';
import { cn } from '@/lib/utils';
import { Calendar } from '@/components/ui/calendar';
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover';

export default function WaiterLoginPage() {
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
        dob: z.date({ required_error: 'Date of birth is required' }),
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
    <main className='flex flex-col justify-center w-full items-center gap-6 lg:flex-row'>
      <section className='flex flex-col justify-center items-center w-full h-full gap-8 text-center lg:items-start p-8'>
        <AuthNav />

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
              name='dob'
              render={({ field }) => (
                <FormItem className='flex flex-col'>
                  <FormLabel>Date of birth</FormLabel>
                  <Popover>
                    <PopoverTrigger asChild>
                      <FormControl>
                        <Button
                          variant={'outline'}
                          className={cn(
                            'w-[240px] pl-3 text-left font-normal',
                            !field.value && 'text-muted-foreground'
                          )}
                        >
                          {field.value ? (
                            format(field.value, 'PPP')
                          ) : (
                            <span>Pick a date</span>
                          )}
                          <CalendarIcon className='ml-auto h-4 w-4 opacity-50' />
                        </Button>
                      </FormControl>
                    </PopoverTrigger>
                    <PopoverContent className='w-auto p-0' align='start'>
                      <Calendar
                        mode='single'
                        selected={field.value}
                        onSelect={field.onChange}
                        disabled={(date) =>
                          date > new Date() || date < new Date('1900-01-01')
                        }
                        initialFocus
                      />
                    </PopoverContent>
                  </Popover>
                  <FormDescription>
                    Your date of birth is used to calculate your age.
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

        <Toaster />
      </section>

      <AuthBanner />
    </main>
  );
}
