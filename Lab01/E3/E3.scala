
object E3 {

    def sommaQuadrati(x:Int, y:Int):Int = {
        if (x==y) Math.pow(x, 2).intValue else Math.pow(x,2).intValue +sommaQuadrati(x+1, y) 
    }
}