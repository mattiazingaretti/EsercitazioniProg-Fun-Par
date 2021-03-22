case class Film(id:Int, titolo:String, anno:Int)
case class Regista(id:Int, nome:String)
case class DirettoDa(idFilm:Int, idRegista:Int)

case class DB(film:List[Film], registi:List[Regista], regie:List[DirettoDa]) {
    def getFilms() = film
    def getRegie() = regie
    def getRegisti() = registi

    def registiConFilm(p:Film=>Boolean):List[Regista] = {
        val films = this.getFilms
        val regie = this.getRegie
        val registi = this.getRegisti

        //esegui p per ogni film in films e riduci la lista ai film che soddisfano p 
        def getIdFilmsWherePropTrue(f:List[Film], i:Int):List[Int] = {
            if (f == Nil || i == f.size ) Nil
            else if ( p(f(i)) ) f(i).id::getIdFilmsWherePropTrue(f, i+1)
            else getIdFilmsWherePropTrue(f , i+1)
        }

        val id_film_ptrue = getIdFilmsWherePropTrue(films, 0)
        
        //Controlla sulla lista "regie" a cosa corrisponde id_film della lista di id di film dove vale p
        if(id_film_ptrue.size > 0) registi.filter(regista => regie.filter(item => id_film_ptrue.contains(item.idFilm)).map(item => item.idRegista).distinct.contains(regista.id) )
        else Nil
         
    }
}
