/*
Scrivere una funzione maxPrefisso(l:List[Int], x:Int):Int Scala che restituisce il più grande numero n
tale che la somma dei primi n numeri di l è minore o uguale a x. 
Ad esempio, maxPrefisso(List(1,1,1,1,1),3) == 3, maxPrefisso(List(5,2,4,7),8)==2 e maxPrefisso(List(5,2,4,7),4)==0.

*/

object E3 extends App{
    
    def maxPrefisso(l:List[Int], x:Int) = {
        
        def aux(list:List[Int], x:Int , n:Int, t:Int):Int = {
            if (list.isEmpty ) 0
            else if (t+list(0) > x ) n
            else aux(list.tail , x , n+1, t+list.head)
        }
        aux(l, x , 0 , 0)
    }
}