// Learn more about F# at http://fsharp.org

open System

// Given an integer n checks if it prime or not.
let isPrime n =
    let limit = int(sqrt(float(n)))+1
    let rec test n x =
        if x>=limit then true
        else if n%x = 0 then false
        else test n (x+1)
    in
        test n 2
// Iterative version of funtion isPrime
let isPrimeI x =
    let limit = int(sqrt(float(x)))+1
    let mutable count = 0
    let mutable isPrime = true
    while (count < limit) && isPrime do 
        isPrime <- (x%count)=0
        count <- count+1
    isPrime

// Given two integers x and y this function computes
// then pair (z,p) such that: (y**p)*z=x
let rec divP x y = 
    if x%y<>0 then (x,0)
    else 
        let (z,p) = divP (x/y) y
        in 
            (z,p+1) 

// Given an integer n computes the list of its 
// prime factors. 
let primeFactors n =
    let rec _primeFactors x y =
        if x<2 then []
        else 
            match divP x y with
            | (_,0) -> _primeFactors x (y+1)
            | (z,p) -> y::(_primeFactors z (y+1))
    _primeFactors n 2                

// Iterative version of primeFactors.
let primeFactorsI n =
    let mutable count = 2
    let mutable v = n
    let mutable res = []
    while v >= count do 
        let (z,p) = divP v count 
        if p>0 then 
            v <- z; 
            res <- (count::res)
        count <- count+1
    List.rev(res) 

let listToString l = 
    List.fold (fun x y -> x+" "+(string y)) "" l        

[<EntryPoint>]
let main argv =
    let mutable flag = true
    let mutable v = 0
    while flag do
        printf "Insert an integer: ";
        v <- (Console.ReadLine () |> int)
        if isPrime v then printf "%d is a prime number!\n" v
        else 
            printf "%d is not a prime number!\n" v;
            printf "Its divisors are%s\n" (listToString (primeFactorsI v))
        printf "Digit 'Q' to quit!\n";
        flag <- (Console.ReadLine () <> "Q")            

    printf "\n\nGoodbye!\n"
    0 // return an integer exit code
