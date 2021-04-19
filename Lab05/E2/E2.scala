sealed abstract class Tree[A] {

    def mapTree[B](t:Tree[B], f:A=>B):Boolean = {
        if (t == E() && this == E()) true 
        else t match {
            case T(l , e , r) => this match{
                                    case T(l1, e1, r1) => if(e != f(e1) ) false else  l1.mapTree(l, f) && r1.mapTree(r, f) 
                                    case E() => false
                                }
            case E() => this match {
                                    case T(l2, e2, r2 ) => false
                                }
        }
    }
}

case class T[S](l:Tree[S], e:S, r:Tree[S]) extends Tree[S]
case class E[S]() extends Tree[S]
