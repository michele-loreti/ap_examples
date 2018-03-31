// Learn more about F# at http://fsharp.org

open System


let fib n = 
    let rec _fib x =
        if (x<=2) then
            (1,1)
        else
            let a,b = _fib (x-1)
            in
                a+b,a
    let a,_ = _fib n
    a

[<EntryPoint>]
let main argv =
    let mutable flag = true
    while flag do
        printf "Insert an integer: ";
        let n = Console.ReadLine () |> int
        printf "fib(%d)=%d" n (fib n)
        printf "\n\nDigit 'Q' to quit!\n"
        if (Console.ReadLine ()="Q") then flag <- false
    printfn "\n\nGoodbye!"
    0 // return an integer exit code
