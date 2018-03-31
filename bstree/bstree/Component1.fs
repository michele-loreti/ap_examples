namespace bstree

open System

module Bstree =

    type bstree<'T when 'T:comparison> = 
        EMPTY
        | BSTREE of value: 'T * left: 'T bstree * right: 'T bstree

    let rec add v t =
        match t with
        EMPTY -> BSTREE(v,EMPTY,EMPTY)
        | BSTREE(v1,l,r) when v<v1 -> BSTREE(v1,add v l,r)
        | BSTREE(v1,l,r) -> BSTREE(v1,l,add v r)            
     
    let rec contains v t = 
        match t with 
        EMPTY -> false
        | BSTREE(v1,_,_) when v1 = v -> true
        | BSTREE(v1,l,_) when v<v1 -> contains v l
        | BSTREE(v1,_,r) -> contains v r

    let rec getAllLessThan v t =
        match t with 
        | EMPTY -> EMPTY
        | BSTREE(v1,l,r) when v1<v -> BSTREE(v1,l,getAllLessThan v r)
        | BSTREE(v1,l,r) -> getAllLessThan v l

    let rec getAllGreaterThan v t =
        match t with 
        | EMPTY -> EMPTY
        | BSTREE(v1,l,r) when v1<v -> getAllGreaterThan v r
        | BSTREE(v1,l,r) -> BSTREE(v1,getAllGreaterThan v l,r)

    let rec merge t1 t2 =
        match t1,t2 with
        | EMPTY,_ -> t2
        | _,EMPTY -> t1
        | BSTREE(v1,l1,r1),BSTREE(v2,l2,r2) when v1<v2 -> 
            let l11 = getAllLessThan v2 r1    
            let l12 = getAllGreaterThan v2 r1
            let l21 = getAllLessThan v1 l2
            let l22 = getAllGreaterThan v1 l2 
            BSTREE(v2,BSTREE(v1,merge l1 l21,merge l11 l21),merge l12 r2)
        | BSTREE(v1,l1,r1),BSTREE(v2,l2,r2) -> //v1 >= v2 
            let l11 = getAllLessThan v2 r1    
            let l12 = getAllGreaterThan v2 r1
            let l21 = getAllLessThan v1 l2
            let l22 = getAllGreaterThan v1 l2 
            BSTREE(v1,BSTREE(v2,l2,merge l11 l21),merge l12 r1)

    let rec getMin t =
        match t with 
        EMPTY -> None
        | BSTREE(v1,EMPTY,_) -> Some v1
        | BSTREE(v1,t1,_) -> getMin t1

    let rec getMax t =
        match t with 
        EMPTY -> None
        | BSTREE(v1,_,EMPTY) -> Some v1
        | BSTREE(v1,_,t1) -> getMax t1
             
    let rec remove v t = 
        match t with 
          EMPTY -> EMPTY
        | BSTREE(v1,l,r) when v<v1 -> BSTREE(v1, remove v l, r)
        | BSTREE(v1,l,r) when v>v1 -> BSTREE(v1, l, remove v r)
        | BSTREE(v1,EMPTY,r) -> r
        | BSTREE(v1,l,EMPTY) -> l 
        | BSTREE(v1,l,r) -> 
            let mv = getMax l 
            let l1 = remove mv.Value l
            BSTREE(mv.Value,l1,r)

    let rec size t = 
        match t with 
        EMPTY -> 0
        | BSTREE(_,l,r) -> 1+(size l)+(size r)

    let rec height t =
        match t with
        EMPTY -> 0
        | BSTREE(_,l,r) -> 1+(max (height l) (height r))

    let rec listOf t =
        match t with
        EMPTY -> []
        | BSTREE(v1,l,r) -> (listOf l)@(v1::(listOf r))

    let arrayOf t = 
        List.toArray (listOf t)

    let fromArray<'T when 'T:comparison>  (a: 'T []) =
        let rec _fromArray i j =
            if j<=i then EMPTY
            else
                let m = i+(j-i)/2
                let v = a.[m]
                BSTREE(v,_fromArray i m, _fromArray (m+1) j)
        _fromArray 0 (Array.length a)

    let balance t = fromArray (arrayOf t)

    let print f t =
        let rec _print str t =
            match t with
            | EMPTY -> printf "%s _\n" str
            | BSTREE(v,l,r) -> 
                printf "%s %s\n" str (f v);
                _print (str+"\t") l;
                _print (str+"\t") r;
        _print "" t
