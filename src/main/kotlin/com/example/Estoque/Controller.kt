package com.example.Estoque

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.beans.factory.annotation.Autowired

// DTO para receber apenas a quantidade no JSON
data class EntradaDTO(val quantidade: Int)

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = ["*"]) // Importante para o React conseguir acessar
class ProdutoController(@Autowired val repository: ProdutoRepository) {

    // 1. Listar
    @GetMapping
    fun listar(): List<Produto> = repository.findAll()

    // 2. Criar Produto
    @PostMapping
    fun salvar(@RequestBody produto: Produto): Produto = repository.save(produto)

    // 3. Buscar por ID
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Produto> {
        return repository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    // 4. REALIZAR ENTRADA (Soma ao estoque atual)
    @PutMapping("/{id}/entrada")
    fun entradaEstoque(@PathVariable id: Long, @RequestBody dados: EntradaDTO): ResponseEntity<Produto> {
        return repository.findById(id).map { produto ->
            produto.quantidade += dados.quantidade // Lógica de soma
            ResponseEntity.ok(repository.save(produto))
        }.orElse(ResponseEntity.notFound().build())
    }

    // 5. Excluir
    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id: Long): ResponseEntity<Void> {
        repository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}