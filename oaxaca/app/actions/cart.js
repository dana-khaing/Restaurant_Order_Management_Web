'use server';

import { cookies, headers } from 'next/headers';
import { SERVICE_URLS } from '../constants';

export async function fetchCart() {
  const res = await fetch(`${SERVICE_URLS.CART_SERVICE}/cart/fetch`, {
    headers: headers(),
  });

  const cart = await res.json();
  return cart;
}

// this.id = id;
// this.productId = productId;
// this.quantity = quantity;
// this.price = price;
// this.productName = productName;
// this.dietaryRequirement = dietaryRequirement;

export async function addToCart(item) {
  const cookieStore = cookies();
  const jseesionId = cookieStore.get('JSESSIONID').value;

  console.log(item);
  console.log(jseesionId);

  const res = await fetch(`${SERVICE_URLS.CART_SERVICE}/cart/addItem`, {
    method: 'POST',
    headers: {
      Cookie: `JSESSIONID=6BA0A708574F26A50B5C53CAB9308D63`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(item),
  });

  const cart = await res.json();

  console.log(cart);
  return cart;
}
