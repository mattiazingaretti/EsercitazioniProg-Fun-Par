// Scrivere un metodo currificato `def buildMatrix(rows:Int, cols:Int)(f:(Int,Int) => Double):Vector[Vector[Double]]` che,
// dato un numero di righe `rows` e un numero di colonne `cols` e una funzione `f` da coppie di indici `(i,j)` 
// a `Double`, restituisce un `Vector[Vector[Double]]` `v` tale che per ogni `i` in `[0,rows-1]` e per ogni `j` in
// `[0,cols-1]` si ha `v(i)(j) == f(i,j)`.

// Scrivere la soluzione qui...

object E1{
    def buildMatrix(rows:Int , cols:Int)(f:(Int,Int)=>Double):Vector[Vector[Double]] = {
        for (i <-  (0 to rows-1).toVector ) yield for ( j<- (0 to cols-1).toVector) yield f(i,j)
    }
}

