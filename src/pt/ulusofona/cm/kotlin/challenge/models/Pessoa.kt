package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.*


class Pessoa : Movimentavel {
    var nome: String private set
    var veiculos: MutableList<Veiculo> private set
    var dataDeNascimento: Date private set
    var carta: Carta? private set
    var posicao: Posicao private set

    constructor(nome: String, dataDeNascimento: Date) {
        this.nome = nome
        this.dataDeNascimento = dataDeNascimento

        // Geridos por mim
        this.veiculos = mutableListOf<Veiculo>()
        this.carta = null
        this.posicao = Posicao(0, 0)
    }

    override fun toString(): String {
        val dtFormatada: String = SimpleDateFormat("dd-MM-yyyy").format(dataDeNascimento)
        return "Pessoa | $nome | $dtFormatada | Posicao | x:${posicao.x} | y:${posicao.y}"
    }

    fun comprarVeiculo(veiculo: Veiculo) {
        veiculos.add(veiculo)
        veiculo.atualizarDataAquisicao()
    }

    fun pesquisarVeiculo(identificador: String): Veiculo {
        val v: Veiculo? = veiculos.find { it.identificador == identificador }

        if (v == null) {
            throw VeiculoNaoEncontradoException()
        }

        return v
    }

    fun venderVeiculo(identificador: String, comprador: Pessoa) {
        val veiculoAVender = pesquisarVeiculo(identificador)

        // Retira do dono atual
        veiculos.remove(veiculoAVender)

        // Adicionar à lista de veiculos do novo dono
        comprador.comprarVeiculo(veiculoAVender)
    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        val veiculo: Veiculo = pesquisarVeiculo(identificador)

        // Testar contra necessidade de tirar carta para usar o Veiculo
        if (!temCarta() && veiculo.requerCarta()) {
            throw PessoaSemCartaException(this.nome)
        }

        // Movimentar ambos
        veiculo.moverPara(x, y)
        this.moverPara(x, y)
    }

    fun temCarta(): Boolean {
        return carta != null
    }


    fun tirarCarta() {
        // Verificar se menor de idade... Se for, não pode tirar Carta ~> lançar Excecao

        //val dtHoje: Date = Date.from(Instant.now())
        //val diffInMillies: Long = abs(dtHoje.time - dataDeNascimento.time)
        //val diff = TimeUnit.

        // CREDITS: https://stackoverflow.com/questions/38967422/calculate-age-from-birthdate
        //          By: utilizador "deHaar"

        val dtNasc: LocalDate = dataDeNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dtHoje: LocalDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate()
        val idadeAnos: Int = Period.between(dtNasc, dtHoje).years

        if (idadeAnos < 18) {
            // A Pessoa ainda não tem 18 Anos
            throw MenorDeIdadeException()
        }

        // Atribuir carta de conducao caso tenha a idade minima permitida.
        carta = Carta()
    }

    // @ Interface Movimentável
    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicaoPara(x, y)
    }
}