

object E1 {
    def annataPiuVecchia(produttori:List[Produttore],
                         vitigni:List[Vitigno],
                         vini:List[Vino],
                         produttore:String):Option[Int] = {
        
        val prodCheck = produttori.find(p => p.nome == produttore )
        if (prodCheck.isEmpty == false){
                val viniDelProd = vini.filter(v => v.idProd == prodCheck.get.idProd)
                val annateProd = viniDelProd.map(v => v.annata)
                val annataMinima = viniDelProd.minBy(v => v.annata).annata        
                val ris = annateProd.find(annata => annata == annataMinima )
                ris
        }else{
            None
        }
        
    }

}
