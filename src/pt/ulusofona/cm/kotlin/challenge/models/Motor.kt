package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoDesligadoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoLigadoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel

class Motor : Ligavel {
    var cavalos: Int private set
    var cilindrada: Int private set
    var ligado: Boolean private set

    constructor(cavalos: Int, cili: Int) {
        this.cavalos = cavalos
        this.cilindrada = cili

        // Geridos por mim
        ligado = false
    }

    override fun toString(): String {
        return "Motor | $cavalos | $cilindrada"
    }

    // @ Interface Ligavel
    override fun ligar() {
        if (this.ligado) {
            throw VeiculoLigadoException()
        }
        this.ligado = true
    }

    override fun desligar() {
        if (!this.ligado) {
            throw VeiculoDesligadoException()
        }
        this.ligado = false
    }

    override fun estaLigado(): Boolean {
        return ligado
    }
}