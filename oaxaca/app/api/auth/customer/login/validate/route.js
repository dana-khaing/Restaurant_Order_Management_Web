import { SERVICE_URLS } from '@/app/constants';
import { cookies } from 'next/headers';

export async function GET() {
  const cookieStore = cookies();
  const remember_me = cookieStore.get('remember-me');

  if (!remember_me) {
    return new Response(
      JSON.stringify({ error: 'No remember-me token found' }),
      {
        status: 400,
        headers: { 'Content-Type': 'application/json' },
      }
    );
  }

  try {
    const res = await fetch(`${SERVICE_URLS.CUSTOMER_SERVICE}/customer/me`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Cookie: `remember-me=${remember_me.value}`,
      },
    });

    if (!res.ok) {
      console.error(await res.text()); // Log or handle error response as text
      return new Response(
        JSON.stringify({ error: 'Request failed with status ' + res.status }),
        {
          status: res.status,
          headers: { 'Content-Type': 'application/json' },
        }
      );
    }

    const response = await res.json();

    const user = {
      id: response.id,
      username: response.username,
      name: response.name,
      email: response.email,
      address: response.address,
      phone: response.phone,
    };
    console.log('response', user);
    return new Response(JSON.stringify(user), {
      status: 200,
      headers: { 'Content-Type': 'application/json' },
    });
  } catch (error) {
    console.error(error);
    return new Response(JSON.stringify({ error: error.toString() }), {
      status: 500,
      headers: { 'Content-Type': 'application/json' },
    });
  }
}
