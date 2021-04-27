// Scrivere una versione parallela `fibPar` del metodo `fib` definito nel file `Fib.scala` usando
// fork-join in Scala mediante il costrutto `par`.

// Scrivere la soluzione qui...
import Par._
import Fib._


object E2 {
    
    def fibPar(a:Int, b:Int)(n:Int) = {
        if (n < 2 ) a 
        else if(n == 2 ) b
        else {
            val ret = Par.par( Fib.fib(1,0)(n) )( Fib.fib(0,1)(n) ) 
            ret._1 + ret._2
        }
    }
    
}