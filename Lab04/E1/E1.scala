object E1 {
 
    def scalarProd(a:Seq[Double], b:Seq[Double]) = {
        val it:Int = Math.min(a.size, b.size)
        
        def aux(x:Seq[Double], y:Seq[Double],i:Int, max:Int):Double = {
            if(i == max) 0
            else a(i)*b(i)+aux(x.tail, y.tail, i+1 , max)  
        }
        aux(a,b, 0,it)
    }
}

