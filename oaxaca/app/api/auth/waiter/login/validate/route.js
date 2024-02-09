import { cookies } from "next/headers";
export async function GET() {

	try {
		const res = await fetch(`http://localhost:8082/waiter/validate-remember-me`, {
			method: "GET",
			headers: { "Content-Type": "application/json" },
		});

		if (!res.ok) {
			console.error(await res.text()); // Log or handle error response as text
			return new Response(JSON.stringify({ error: "Request failed with status " + res.status }), {
				status: res.status,
				headers: {
					"Content-Type": "application/json",
				},
			});
		}

		const response = await res.text();
		console.log(response)
		return new Response(JSON.stringify(response), {
			status: 200,
			headers: { "Content-Type": "application/json" },
		});
	} catch (error) {
		console.error(error);
		return new Response(JSON.stringify({ error: error.toString() }), {
			status: 500,
			headers: { "Content-Type": "application/json" },
		});
	}
}



