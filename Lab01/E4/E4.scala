object E4 {
    
    def ugualiIn(f1:Int=>Int,f2:Int=>Int, n:Int):Boolean = {
        if (n < 0) true
        else if (f1(n) != f2(n)) false
        else ugualiIn(f1, f2, n-1) 
    }
}