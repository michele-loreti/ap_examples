// Learn more about F# at http://fsharp.org
// See the 'F# Tutorial' project for more help.

// 0 1 2 3 4 5 6 7 8 9 . + - * / =

type digit = ZERO | ONE | TWO | THREE | FOUR | FIVE | SIX | SEVEN | EIGHT | NINE 

type symbol  = PLUS | MINUS | TIME | DIV | DOT | EQUAL

type statement = DIGIT of digit | OPERATION of symbol

type program = statement list 

type value = INTVALUE of int | FLOATVALUE of float

type calcstate = { view: value ; mem: value }

let valueOf d =
    match d with 
    ZERO -> 0
    | ONE -> 1
    | TWO -> 2
    | THREE -> 3
    | FOUR -> 4
    | FIVE -> 5 
    | SIX -> 6
    | SEVEN -> 7
    | EIGHT -> 8
    | NINE -> 9

let apply fint ffloat v1 v2 = 
    match v1,v2 with
    INTVALUE(x),INTVALUE(y) ->  INTVALUE(fint x y)
    |FLOATVALUE(x),FLOATVALUE(y) -> FLOATVALUE(ffloat x y)
    |FLOATVALUE(x),INTVALUE(y) -> FLOATVALUE(ffloat x (float y))
    |INTVALUE(x),FLOATVALUE(y) -> FLOATVALUE(ffloat (float x) y)

let toFloat v =
    match v with
    INTVALUE(x) -> FLOATVALUE(float x)
    | FLOATVALUE(_) -> v


let addDigit s d =  
    let _addDigit v d =
        match v with
        INTVALUE(x) -> INTVALUE(10*x+ (valueOf d))
        | FLOATVALUE(x) -> FLOATVALUE(10.0*x+(float (valueOf d)))
    { view = (_addDigit s.view d) ; mem = s.mem }

let evaluateSymbol s o = 
    match o with
    PLUS -> { view = INTVALUE(0) ; mem = (apply (fun x y -> x+y) (fun x y -> x+y) s.mem s.view) } 
    | MINUS -> { view = INTVALUE(0) ; mem = (apply (fun x y -> x-y) (fun x y -> x-y) s.mem s.view) } 
    | TIME -> { view = INTVALUE(0) ; mem = (apply (fun x y -> x*y) (fun x y -> x*y) s.mem s.view) } 
    | DIV -> { view = INTVALUE(0) ; mem = (apply (fun x y -> x/y) (fun x y -> x/y) s.mem s.view) } 
    | DOT -> { view = toFloat s.view ; mem = s.mem }
    | EQUAL -> { view = s.mem ; mem = INTVALUE(0) }

let doStatement cs s =
    match s with
    DIGIT(d) -> addDigit cs d
    | OPERATION(o) -> evaluateSymbol cs o

let execute p = 
    let mutable cs = { view = INTVALUE(0) ; mem = INTVALUE(0) }
    for c in p do
        cs <- doStatement cs c
    cs.view

        
[<EntryPoint>]
let main argv = 
    printfn "%A" argv
    0 // return an integer exit code
