import { SERVICE_URLS } from '../constants';

export async function fetchCart() {
  const res = await fetch(`${SERVICE_URLS.CART_SERVICE}/cart/fetch`);
  const cart = await res.json();
  return cart;
}
