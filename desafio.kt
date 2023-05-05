
// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String) {
    val conteudosInscritos = mutableListOf<Formacao>()
    val conteudosConcluidos = mutableListOf<Formacao>()

    fun progredir() {
        val conteudo = this.conteudosInscritos.stream().findFirst()
        if (!conteudo.isPresent){
            println("Você não está matriculado em nenhum conteúdo!")
        }
        this.conteudosConcluidos.add(conteudo.get())
        this.conteudosInscritos.remove(conteudo.get())
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (nome != other.nome) return false
        if (conteudosInscritos != other.conteudosInscritos) return false
        return conteudosConcluidos == other.conteudosConcluidos
    }

    override fun hashCode(): Int {
        var result = nome.hashCode()
        result = 31 * result + conteudosInscritos.hashCode()
        result = 31 * result + conteudosConcluidos.hashCode()
        return result
    }

    override fun toString(): String {
        return "Usuario(nome='$nome', conteudosInscritos=$conteudosInscritos, conteudosConcluidos=$conteudosConcluidos)"
    }


}

data class ConteudoEducacional(var nome: String, val duracao: Int, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    private val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
//        TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
        inscritos.add(usuario).also { usuario.conteudosInscritos.add(this) }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Formação: $nome / Conteúdos: (")
        for (i in conteudos.indices) {
            if (i == conteudos.size -1) sb.append("${conteudos[i].nome}) / ")
            else sb.append("${conteudos[i].nome}, ")
        }
        sb.append("Inscritos: (")
        for (i in inscritos.indices) {
            if (i == inscritos.size -1) sb.append("${inscritos[i].nome})")
            else sb.append("${inscritos[i].nome}, ")
        }

        return sb.toString()
    }
}

fun main() {
//    TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
//    TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")

    val usuario1 = Usuario("Jolteon")
    val usuario2 = Usuario("Flareon")
    val usuario3 = Usuario("Pikachu")

    val conteudoBasico1 = ConteudoEducacional("Java",40, Nivel.BASICO)
    val conteudoBasico2 = ConteudoEducacional("JavaScript",50, Nivel.INTERMEDIARIO)
    val conteudoBasico3 = ConteudoEducacional("Kotlin",60, Nivel.AVANCADO)
    val conteudoBasico4 = ConteudoEducacional("CSS",30, Nivel.BASICO)


    val formacaoBackEndBasico = Formacao("Back-End - Básico", listOf(conteudoBasico1, conteudoBasico3))
    val formacaoFrontEndBasico = Formacao("Front-End - Básico", listOf(conteudoBasico2, conteudoBasico4))

    formacaoBackEndBasico.matricular(usuario1)
    formacaoBackEndBasico.matricular(usuario2)
    formacaoFrontEndBasico.matricular(usuario1)
    formacaoFrontEndBasico.matricular(usuario3)

    println("Dev: ${ usuario1.nome }")
    println("--- Conteúdos Inscritos ---")
    for (conteudo in usuario1.conteudosInscritos) println (conteudo)
    println()
    println("--- Conteúdos Concluidos ---")
    for (conteudo in usuario1.conteudosConcluidos) println (conteudo)
    println()

    usuario1.progredir().also {
        println("--- Progredindo ---")
        println()
    }

    println("--- Conteúdos Inscritos ---")
    for (conteudo in usuario1.conteudosInscritos) println (conteudo)
    println()

    println("--- Conteúdos Concluidos ---")
    for (conteudo in usuario1.conteudosConcluidos) println (conteudo)
    println()


}

