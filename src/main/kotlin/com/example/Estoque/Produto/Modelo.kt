package com.example.Estoque.Produto
// Define o pacote onde esta classe está localizada.

import jakarta.persistence.*
// Importa as anotações JPA necessárias para mapear classes para o banco de dados.


// ----------------------
// CLASSE PAI (Herança)
// ----------------------

// @MappedSuperclass indica que esta classe NÃO vira uma tabela no banco,
// mas suas propriedades serão herdadas pelas classes filhas que forem @Entity.
@MappedSuperclass
abstract class EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Define que o campo ID é a chave primária e será gerado automaticamente no BD.
    var id: Long? = null

    // Método abstrato: obriga as subclasses a implementarem esse método.
    // Demonstra polimorfismo: cada classe filha pode fornecer sua própria versão.
    abstract fun getDescricao(): String
}



// ----------------------
// CLASSE FILHA (Produto)
// ----------------------

@Entity
// Marca a classe como uma tabela no banco de dados através do JPA.
class Produto(
    // Atributo do produto que será mapeado para uma coluna da tabela.
    var nome: String = "",

    // Preço do produto.
    var preco: Double = 0.0,

    // Quantidade em estoque.
    var quantidade: Int = 0
) : EntidadeBase() {   // ← Produto HERDA de EntidadeBase

    // Sobrescrita do método abstrato da classe pai.
    // Implementação específica para Produto.
    override fun getDescricao(): String {
        return "$nome - Estoque: $quantidade"
        // Retorna uma descrição formatada do produto.
    }
}
