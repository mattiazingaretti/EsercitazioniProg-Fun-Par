
object E1 extends App{    
    def sommaFun(f1:Double=>Double , f2:Double=> Double):Double=>Double ={ (k:Double)=>f1(k)+f2(k) }
}


