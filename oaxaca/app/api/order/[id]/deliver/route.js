import { SERVICE_URLS } from "@/app/constants";

export async function PUT(request, params) {
    const id = params.params.id;
    console.log("Params: ", params )
    console.log("Deliver order id: ", id);

    try {
        const response = await fetch(
            `${SERVICE_URLS.ORDER_SERVICE}/orders/deliverOrder/${id}`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "PUT",
            }
        );

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

        console.log("Order deliver status:", data);

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
