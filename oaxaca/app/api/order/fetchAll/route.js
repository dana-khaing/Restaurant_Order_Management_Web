
export async function GET(){
    
    try {
        const response = await fetch(
            `http://localhost:8086/orders/fetchAll`,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
                cache: "no-store",
            }
        );

        if(!response.ok) {
            const errorJson = await response.json();
            console.log(errorJson);

            return new Response(JSON.stringify(errorJson), {
                status: response.status,
                statusText: response.statusText,
            });
        }

        const data = await response.json();

        console.log("Route data: ", data);

        return new Response(JSON.stringify(data), {
            status: response.status,
            statusText: response.statusText,
        });

    } catch(error) {
        console.error(error);
        return new Response(
            JSON.stringify({
                message: "Internal Server Error",
            }),
            {
                status: 500,
                statusText: "Internal Server Error",
            }
        );
    }
}