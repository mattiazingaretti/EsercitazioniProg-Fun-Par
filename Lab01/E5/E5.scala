object E5 {

    def somma(f:Int=>Int) = {
        def ret(a:Int, b:Int):Int = {
            if (a == b ) f(a) else f(a) + ret(a+1, b)
        }
        ret _
    }
}