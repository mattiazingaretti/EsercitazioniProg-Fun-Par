  
    def getInc(list:List[Int], inc:List[Int]):List[Int] = {
        if (list==Nil) inc
        else if (list.length < 2 ) inc:+list.head
        else if (list.head < list.tail.head ) getInc(list.tail, inc:+list.head)
        else  inc:+list.head 
    }

  //Controllo che nei restanti elementi non vi siano elementi crescenti
    def getDecl(list:List[Int], dec:List[Int]):List[Int] = {
        if (list.isEmpty) dec
        else if (list.length < 2 ) dec:+list.head
        else if (list.head > list.tail.head) getDecl(list.tail, dec:+list.head)
        else Nil //Per definizione di lista bitonic
    }
    
      