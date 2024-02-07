
export default function AuthHeader({text}){

    return(

        <div className="flex flex-col justify-center items-center w-full gap-4 lg:items-start">
                    <h1 className="text-2xl font-semibold text-orange-500">
                        {" "}
                        {text}{" "}
                    </h1>

                    <p className="text-gray-500">
                        {" "}
                        {text} to access your Oaxaca account{" "}
                    </p>
                </div>
    )
}