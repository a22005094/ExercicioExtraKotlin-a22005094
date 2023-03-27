package pt.ulusofona.cm.kotlin.challenge.models

import java.text.SimpleDateFormat

class Bicicleta : Veiculo {

    constructor(identificador: String) : super(identificador)

    override fun toString(): String {
        val dtFormatada: String = SimpleDateFormat("dd-MM-yyyy").format(dataDeAquisicao)
        return "Bicicleta | $identificador | $dtFormatada | Posicao | x:${posicao.x} | y:${posicao.y}"
    }

    override fun requerCarta(): Boolean {
        return false
    }
}