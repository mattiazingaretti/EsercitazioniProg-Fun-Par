// Scrivere un metodo `def subList(l:List[T]):Boolean` applicabile su un oggetto `List[T]` `s` che restituisce `true` 
// se e solo se tutti gli elementi di `l` appaiono anche in `s` nello stesso ordine, anche non consecutivamente.

import scala.language.implicitConversions

object E3 { 
    implicit def seq2MySeq[T](s:List[T]):MyList[T] = new MyList(s)
}

class MyList[T](s:List[T]) {
	import E3._
        
        def subList(l:List[T]):Boolean = {
            
            def aux[T](l:List[T], s:List[T], m:Int, len:Int):Boolean = {
                if ((l == Nil || s == Nil) && m == len ) true
                else if ((l == Nil || s == Nil)&& m != len ) false
                else if (l.head == s.head) aux(l.tail , s.tail, m+1, len)
                else aux(l , s.tail , m, len)
                
            }
            
            if (l.size > this.s.size || this.s.size == 0) false
            else aux(l, this.s , 0 ,l.size)   
        }

}
