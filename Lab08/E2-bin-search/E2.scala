sealed abstract class Tree {

	def lTree():List[Int] = {
		this match{
			case E() => Nil
			case T(s,e,d) => e::(s.lTree() ::: d.lTree())
		}
	}

	def getMin():Int = { 
		val l = this.lTree()
		if (l.size > 0 ) l.min  
		else 0
	}

	def getMax():Int = {
		val l = this.lTree() 
		if (l.size > 0 ) l.max  
		else 0
	}
	
	def treeTest:Boolean = {
		val t:Tree = this
		val max = t match{
			case E() => 0
			case T(s,e,d) => s.getMax()
		}
		val min = t match{
			case E() => 0
			case T(s,e,d) => d.getMin()
		}

		def aux(t:Tree):Boolean = {
			t match {
				case E() => true
				case T(s, e , d) => if ( e < max || e > min) false else aux(s) && aux(d)
			}
		}
		aux(t)
	}

}

// albero non vuoto
case class T(l:Tree, e:Int, r:Tree) extends Tree 

// albero vuoto
case class E() extends Tree
