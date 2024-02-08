export async function POST(request) {
	const data = await request.json();

	try {
		const res = await fetch(`http://localhost:8081/kitchen_staff/login`, {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(data),
		});

		if (!res.ok) {
			console.error(await res.text()); // Log or handle error response as text
			return new Response(JSON.stringify({ error: "Request failed with status " + res.status }), {
				status: res.status,
				headers: { "Content-Type": "application/json" },
			});
		}

		const response = await res.json();
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

