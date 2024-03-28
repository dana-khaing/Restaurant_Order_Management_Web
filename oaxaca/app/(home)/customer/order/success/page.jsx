
import Link from "next/link"

export default function Component() {
  return (
    <div className="flex flex-col min-h-screen py-10 items-center justify-center text-center gap-4">
      <div className="flex flex-col items-center justify-center gap-2">
        <img
          alt="Logo"
          className="aspect-square overflow-hidden rounded-lg object-contain object-center"
          height="120"
          src="/images/logo_sample.png"
          width="120"
        />
        <div className="space-y-2">
          <h1 className="text-3xl font-bold tracking-tighter sm:text-4xl md:text-5xl">Thanks for your order!</h1>
          <p className="mx-auto max-w-[600px] text-gray-500 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed dark:text-gray-400">
            Your payment has been successfully processed.
          </p>
        </div>
      </div>
      <div className="flex flex-col gap-2 min-[400px]:flex-row"> 
        <Link
          className="inline-flex h-10 items-center justify-center rounded-md border border-gray-200 border-gray-200 bg-white px-8 text-sm font-medium shadow-sm transition-colors hover:bg-gray-100 hover:text-gray-900 focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-gray-950 disabled:pointer-events-none disabled:opacity-50 dark:border-gray-800 dark:border-gray-800 dark:bg-gray-950 dark:hover:bg-gray-800 dark:hover:text-gray-50 dark:focus-visible:ring-gray-300"
          href="/menus"
        >
          Return to menu
        </Link>
      </div>
    </div>
  )
}

