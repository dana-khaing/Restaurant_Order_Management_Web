export async function POST(request) {
    const data = await request.json();

    try {
        const res = await fetch(`${process.env.API_URL}/auth/customer/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        });

        if (!res.ok) {
            return new Response(JSON.stringify({ message: "Error" }), {
                status: res.status,
                headers: {
                    "Content-Type": "application/json",
                },
            });
        }

        const response = await res.json();

        return new Response(JSON.stringify(response), {
            status: 200,
            headers: {
                "Content-Type": "application/json",
            },
        });
    } catch (error) {
        return new Response(JSON.stringify({ message: error }), {
            status: 500,
            headers: {
                "Content-Type": "application/json",
            },
        });
    }
}
