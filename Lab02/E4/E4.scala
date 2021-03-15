/*
Una sequenza bitonica è formata da una sequenza non vuota strettamente crescente
seguita da una sequenza non vuota strettamente decrescente
ad esempio: List(1,2,5,6,9,4,3,2,0) è bitonica
, mentre List(1,2,3,2,3,2,1), List(1,2,3) e List() non lo sono.

Scrivere una funzione checkBitonic(l:List[Int]):(List[Int],List[Int]) che,
data una lista l bitonica,
restituisce (inc,dec) tale che inc è il prefisso crescente di l che include l’elemento massimo 
e dec è il suffisso strettamente decrescente che segue 
(si ha che inc ::: dec == l).
Se invece l non è bitonica, la funzione restituisce (Nil,Nil).
*/

object E4 extends App{

    def checkBitonic(l:List[Int]):(List[Int],List[Int]) ={
        //Controllo e salvo crescenza 
        
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
    
        
        //ritorno (inc,decl)
        val inc:List[Int] = getInc(l, Nil)
        val dec:List[Int] = getDecl(l.slice(inc.length , l.length), Nil)

        if (inc == Nil || dec == Nil) (Nil, Nil)
        else (inc,dec)    
    }
}