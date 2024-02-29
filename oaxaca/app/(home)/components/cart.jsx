import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover';
import { ShoppingCart } from 'lucide-react';

export default function Cart({ cartLength }) {
  return (
    <Popover>
      <PopoverTrigger asChild>
        <button variant='outline' className='relative mr-3'>
          <span className='absolute -right-2 -top-2 inline-block h-5 w-5 rounded-full bg-gray-300 text-center text-xs font-medium leading-5 text-black'>
            {cartLength}
          </span>
          <ShoppingCart className='' size={'24px'} />
        </button>
      </PopoverTrigger>
      <PopoverContent className='w-80'></PopoverContent>
    </Popover>
  );
}
