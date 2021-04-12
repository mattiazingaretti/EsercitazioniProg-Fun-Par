object E3 {
    def noobSort[T](v:Vector[T]):Vector[T] = {
        
        def mySorted[T](v:Vector[T]):Vector[T] = {
            v match {
                case v1:Vector[Int] => v1.sorted 
                case v2:Vector[String] => v2.sorted 
                case v3:Vector[Double] => v3.sorted 

            }
        }

        v.permutations.toList.filter(vec => mySorted(vec) == vec).head
    }
}