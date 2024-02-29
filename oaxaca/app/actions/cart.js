'use server';

import { cookies } from 'next/headers';
import { SERVICE_URLS } from '../constants';
import { revalidatePath } from 'next/cache';

export async function fetchCart() {
  const cookieStore = cookies();
  const jsessionId = cookieStore.get('JSESSIONID')?.value;

  console.log('jsessionId', jsessionId);

  try {
    const res = await fetch(`${SERVICE_URLS.CART_SERVICE}/cart/fetch`, {
      headers: {
        Cookie: `JSESSIONID=${jsessionId}`,
        'Content-Type': 'application/json',
      },
    });

    const data = await res.json();
    return data['cart'];
  } catch (e) {
    console.log(e.message);
    return [];
  }
}

export async function addToCart(item) {
  const cookieStore = cookies();
  const jsessionId = cookieStore.get('JSESSIONID')?.value;

  const res = await fetch(`${SERVICE_URLS.CART_SERVICE}/cart/addItem`, {
    method: 'POST',
    headers: {
      Cookie: `JSESSIONID=${jsessionId}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(item),
  });

  revalidatePath('/');
}
