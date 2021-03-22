sealed abstract class Tree {
    
    def treeTest():Boolean = {
        this match {
            case E() => true
            case  T(l, e , r) => (l,e,r ) match{
                case  l   if (l != Nil && e < l.e )=> false
                case  r  if (r != Nil && e > r.e ) => false
                case _ =>  l.treeTest() && r.treeTest()
            }
            
        } 
    }

}

// albero non vuoto
case class T(l:Tree, e:Int, r:Tree) extends Tree 

// albero vuoto
case class E() extends Tree
