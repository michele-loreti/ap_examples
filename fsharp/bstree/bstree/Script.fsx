// Learn more about F# at http://fsharp.org. See the 'F# Tutorial' project
// for more guidance on F# programming.

#load "Component1.fs"
open bstree

let t = Bstree.add 1 Bstree.EMPTY |> Bstree.add 2 |> Bstree.add 3 |> Bstree.add 4 |> Bstree.add 5 |> Bstree.add 6 |> Bstree.add 7

let t1 = Bstree.balance t;;

Bstree.print (fun i -> string i)  t1