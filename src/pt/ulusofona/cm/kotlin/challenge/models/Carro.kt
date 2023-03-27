package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel
import java.text.SimpleDateFormat

class Carro : Veiculo, Ligavel {
    var motor: Motor private set

    constructor(identificador: String, motor: Motor) : super(identificador) {
        this.motor = motor
    }

    override fun toString(): String {
        val dtFormatada: String = SimpleDateFormat("dd-MM-yyyy").format(dataDeAquisicao)
        return "Carro | $identificador | $dtFormatada | Posicao | x:${posicao.x} | y:${posicao.y}"
    }

    // herança de Veiculo
    override fun requerCarta(): Boolean {
        return true
    }

    override fun ligar() {
        this.motor.ligar()
    }

    override fun desligar() {
        this.motor.desligar()
    }

    override fun estaLigado(): Boolean {
        return this.motor.estaLigado()
    }
}