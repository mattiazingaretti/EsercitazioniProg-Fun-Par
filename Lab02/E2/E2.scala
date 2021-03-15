//True Condition b(i)=f(a(i))
object E2 extends App{
    
    def corrisp[A,B](a:List[A], b:List[B], f:A=>B):Boolean ={
        
        if (a.isEmpty || b.isEmpty) true
        else if (b(0)==f(a(0))) corrisp(a.tail , b.tail, f)
        else false
    }
} 
