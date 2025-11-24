package com.example.Estoque.Controller
// Define o pacote onde este controller está localizado.

import com.example.Estoque.Produto.Produto
// Importa a classe Produto, que representa a entidade usada no banco.

import com.example.Estoque.Repository.ProdutoRepository
// Importa o repositório responsável por acessar o banco de dados.

import org.springframework.web.bind.annotation.*
// Importa anotações do Spring para criar endpoints REST.

import org.springframework.http.ResponseEntity
// Classe usada para retornar respostas HTTP completas (status + corpo).

import org.springframework.beans.factory.annotation.Autowired
// Permite injetar dependências automaticamente pelo Spring.


// DTO para receber apenas a quantidade no JSON
// Esse objeto será usado nas requisições de entrada de estoque.
data class EntradaDTO(val quantidade: Int)


// Marca a classe como um controlador REST (retorna JSON automaticamente)
@RestController

// Define o prefixo de rota para todos os endpoints deste controller
@RequestMapping("/api/produtos")

// Libera o acesso CORS de qualquer origem (necessário para o React acessar)
@CrossOrigin(origins = ["*"])

// Controller recebe automaticamente o ProdutoRepository pelo Spring
class ProdutoController(@Autowired val repository: ProdutoRepository) {

    // 1. Listar todos os produtos
    @GetMapping
    fun listar(): List<Produto> = repository.findAll()
    // Retorna a lista de todos os produtos salvos no banco.


    // 2. Criar um novo Produto
    @PostMapping
    fun salvar(@RequestBody produto: Produto): Produto = repository.save(produto)
    // @RequestBody → indica que os dados virão no corpo da requisição (JSON)
    // repository.save(produto) → salva ou atualiza o produto no banco.


    // 3. Buscar produto por ID
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Produto> {
        // Busca o produto no banco pelo ID
        return repository.findById(id)
            .map { ResponseEntity.ok(it) }              // Se encontrar, retorna 200 OK com o produto
            .orElse(ResponseEntity.notFound().build())  // Se não encontrar, retorna 404
    }


    // 4. Realizar entrada de estoque
    @PutMapping("/{id}/entrada")
    fun entradaEstoque(@PathVariable id: Long, @RequestBody dados: EntradaDTO): ResponseEntity<Produto> {

        // Tenta encontrar o produto pelo ID
        return repository.findById(id).map { produto ->

            produto.quantidade += dados.quantidade
            // Soma a quantidade enviada no JSON ao valor atual do estoque

            ResponseEntity.ok(repository.save(produto))
            // Salva a alteração e retorna o produto atualizado

        }.orElse(ResponseEntity.notFound().build()) // Caso o ID não exista: retorna 404
    }

    // 5. ✏️ EDITAR PRODUTO (nome, preço e quantidade)
    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: Long,
        @RequestBody produtoAtualizado: Produto
    ): ResponseEntity<Produto> {

        // Procura o produto pelo ID
        return repository.findById(id).map { produto ->

            // Atualiza os campos
            produto.nome = produtoAtualizado.nome
            produto.preco = produtoAtualizado.preco
            produto.quantidade = produtoAtualizado.quantidade

            // Salva e retorna o atualizado
            ResponseEntity.ok(repository.save(produto))

        }.orElse(ResponseEntity.notFound().build()) // Caso não encontre → retorna 404
    }


    // 5. Excluir um produto pelo ID
    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id: Long): ResponseEntity<Void> {
        repository.deleteById(id) // Remove o produto do banco
        return ResponseEntity.noContent().build() // Retorna 204 No Content
    }
}
