import { cookies } from "next/headers";
import { SERVICE_URLS } from "@/app/constants";

export async function PUT(request, params) {
    const cookieStore = cookies();
    const JSESSIONID = cookieStore.get("JSESSIONID")?.value;
    const id = params.params.id;
    console.log("ID: ", id )
    console.log("Params: ", params);

    try {
        const response = await fetch(
            `${SERVICE_URLS.ORDER_SERVICE}/orderPayment/payOrder/${id}`,
            {
                headers: {
                    "Content-Type": "application/json",
                    Cookie: `JSESSIONID=${JSESSIONID}`,
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

        console.log(data);

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
