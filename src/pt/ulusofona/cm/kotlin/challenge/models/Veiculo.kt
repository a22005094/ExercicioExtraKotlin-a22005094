package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.time.Instant
import java.util.*

abstract class Veiculo : Movimentavel {
    var identificador: String private set
    var posicao: Posicao private set
    var dataDeAquisicao: Date private set


    constructor(identificador: String) {
        this.identificador = identificador

        // Geridos por mim
        this.posicao = Posicao(0, 0)
        this.dataDeAquisicao = Date.from(Instant.now())
    }

    abstract fun requerCarta(): Boolean

    // @ Interface Movimentável
    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicaoPara(x, y)
    }

    // By me...
    fun atualizarDataAquisicao() {
        this.dataDeAquisicao = Date.from(Instant.now())
    }

}