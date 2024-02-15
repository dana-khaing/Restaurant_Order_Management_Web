import { SERVICE_URLS } from '@/app/constants';
import { NextResponse } from 'next/server';

export async function POST(request) {
  const data = await request.json();

  try {
    const res = await fetch(
      `${SERVICE_URLS.KITCHEN_STAFF_SERVICE}/kitchen_staff/login?remember-me=true`,
      {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
      }
    );

    if (!res.ok) {
      console.error(await res.text()); // Log or handle error response as text
      return new Response(
        JSON.stringify({ error: 'Request failed with status ' + res.status }),
        {
          status: res.status,
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );
    }

    const jsessionId = res.headers.getSetCookie('JSESSIONID');
    const remember_me = res.headers.getSetCookie('remember-me');

    console.log('J:', jsessionId);
    console.log('R:', remember_me);

    const response = await res.json();

    return new NextResponse(JSON.stringify(response), {
      status: 200,
      headers: {
        'Content-Type': 'application/json',
        'Set-Cookie': jsessionId,
        'Set-Cookie': remember_me,
      },
    });
  } catch (error) {
    console.error(error);
    return new Response(JSON.stringify({ error: error.toString() }), {
      status: 500,
      headers: { 'Content-Type': 'application/json' },
    });
  }
}
