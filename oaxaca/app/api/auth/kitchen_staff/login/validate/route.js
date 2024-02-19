import { SERVICE_URLS } from '@/app/constants';
import { cookies } from 'next/headers';
export async function GET() {
  const cookieStore = cookies();
  const remember_me = cookieStore.get('remember-me');
  console.log('RM', remember_me);

  try {
    const res = await fetch(
      `${SERVICE_URLS.KITCHEN_STAFF_SERVICE}/kitchen_staff/validate-remember-me`,
      {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Cookie: `remember-me=${remember_me.value}`,
        },
      }
    );

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
    console.log(response);
    return new Response(JSON.stringify(response), {
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
