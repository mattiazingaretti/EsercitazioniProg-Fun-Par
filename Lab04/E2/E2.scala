import scala.language.implicitConversions

object E2{
    implicit def VectorToMyVector[T](v:Vector[T]) = MyVector(v)
}
    
case class MyVector[T](vec:Vector[T]){

    def isMappedFrom[T1]( m:Vector[T1], f:T=>T1 ):Boolean = {
        vec.map(el => f(el)) == m
    }

}   
        
