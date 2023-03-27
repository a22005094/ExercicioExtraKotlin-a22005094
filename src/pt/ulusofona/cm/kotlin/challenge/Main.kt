import pt.ulusofona.cm.kotlin.challenge.models.Pessoa
import java.time.Instant
import java.util.*


fun main() {
    // aqui escreves o código do programa


    var dtLocal: Date = Date.from(Instant.now())

    var pessoa: Pessoa = Pessoa(nome = "Rui", dataDeNascimento = dtLocal)


    pessoa.tirarCarta()
}