package com.example.Estoque

import jakarta.persistence.*

// CLASSE PAI (Herança)
@MappedSuperclass
abstract class EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    // Método abstrato (Polimorfismo)
    abstract fun getDescricao(): String
}

// CLASSE FILHA
@Entity
class Produto(
    var nome: String = "",
    var preco: Double = 0.0,
    var quantidade: Int = 0
) : EntidadeBase() {

    // Sobrescrita obrigatória (Polimorfismo)
    override fun getDescricao(): String {
        return "$nome - Estoque: $quantidade"
    }
}