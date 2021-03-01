// trasformare il seguente medodo in modo che usi la ricorsione di coda

object E1 {
    def sum(n:Int) = {
        @scala.annotation.tailrec
        def aux(n:Int, c:Int):Int = {
            if (n<1) c else aux(n-1, c+n) 
        }
        aux(n, 0)
    }
}

