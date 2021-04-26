// Scrivi la soluzione qui...
object E4 {

    def piuGiovane(s:Vector[Studente], e:Vector[Eta]):Option[String] = {
        s match{
            case Vector() => None
            case _:Vector[Studente] => {
                val id = e.minBy(st => st.eta).id
                val nome = s.find(st => st.id == id).get.nome
                Some(nome)
            } 
        }
    }
}