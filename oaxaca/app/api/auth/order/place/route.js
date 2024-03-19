export default async function POST(request) {
    const orderData = await request.json();

    try {
        const response = await fetch("localhost:8085/orders/placeOrder", {
            headers: {
                "Content-Type": "application/json",
            },
            method: "POST",
            body: JSON.stringify(orderData),
        });

        if (!response.ok) {
            const errorText = await response.json();
            console.error(errorText);
            return new Response(JSON.stringify(errorText), {
                status: 400,
                headers: {
                    "Content-Type": "application/json",
                },
            });
        }

        const data = await response.json();

        return new Response(JSON.stringify(data), {
            headers: {
                "Content-Type": "application/json",
            },
        });
    } catch (error) {
        console.error(error);
        return new Response(JSON.stringify(error), {
            status: 500,
        });
    }
}
