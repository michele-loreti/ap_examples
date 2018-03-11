// Learn more about F# at http://fsharp.org

open System

let readUInt msg =
    printf msg;
    Console.ReadLine () |> uint32

let mcd a b = 
    let rec _mcd x y = 
        if y=0u then x
        else _mcd y (x%y) 
    _mcd (max a b) (min a b)

[<EntryPoint>]
let main argv =
    let v1 = readUInt "Insert v1: "
    let v2 = readUInt "Insert v2: "
    printfn "mcd %d %d = %d" v1 v2 (mcd v1 v2)
    0 // return an integer exit code
