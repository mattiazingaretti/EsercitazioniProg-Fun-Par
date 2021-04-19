import scala.language.implicitConversions


object E3{
    //implicit def MyIntToInt(my:MyInt ):Int = my.i
    implicit def IntToMyInt(int: Int):MyInt = MyInt(int)
}

case class MyInt(val i:Int) {
    
    def isPrime() = {
        def aux(t:Int, i:Int):Boolean = {
            if (i == t ) true
            else if(t % i == 0 ) false
            else aux(t, i+1) && true 
        }

        aux(this.i, 2)
    }
}