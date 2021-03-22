object E2 {
    
    def getModel(n:Int) ={
        
        def aux(num:Int, i:Int):List[Shape] = {
            if (i == num+1 || num == 0 ) Nil
            else  Circle(i/num.toDouble*0.5 ,i/num.toDouble*0.5,i/num.toDouble*0.5)::aux(num, i+1)
        }

        aux(n, 1) 
    }
}
