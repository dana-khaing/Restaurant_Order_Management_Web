import { SERVICE_URLS } from '@/app/constants';

export async function POST(request) {
  const data = await request.json();

  try {
    const res = await fetch(
      `${SERVICE_URLS.CUSTOMER_SERVICE}/customer/register`,
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      }
    );

    if (!res.ok) {
      console.error(await res.text());
      return new Response(JSON.stringify({ message: '' }), {
        status: res.status,
        headers: {
          'Content-Type': 'application/json',
        },
      });
    }

    const response = await res.json();

    return new Response(JSON.stringify(response), {
      status: 200,
      headers: {
        'Content-Type': 'application/json',
      },
    });
  } catch (error) {
    console.error(error);
    return new Response(JSON.stringify({ message: error }), {
      status: 500,
      headers: {
        'Content-Type': 'application/json',
      },
    });
  }
}
