import { SERVICE_URLS } from '@/app/constants';
import { cookies } from 'next/headers';

export async function PUT(request) {
  const cookieStore = cookies();
  const JSESSIONID = cookieStore.get('JSESSIONID')?.value;

  const { id } = await request.json();

  try {
    const response = await fetch(
      `${SERVICE_URLS.ORDER_SERVICE}/orders/sendOrderToKitchen/${id}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Cookie: `JSESSIONID=${JSESSIONID}`,
        },
        method: 'PUT',
      }
    );

    if (!response.ok) {
      const errorText = await response.json();
      console.error(errorText);
      return new Response(JSON.stringify(errorText), {
        status: 400,
        headers: {
          'Content-Type': 'application/json',
        },
      });
    }

    const data = await response.json();

    console.log(data);

    return new Response(JSON.stringify(data), {
      headers: {
        'Content-Type': 'application/json',
      },
    });
  } catch (error) {
    console.error(error);
    return new Response(JSON.stringify(error), {
      status: 500,
    });
  }
}
