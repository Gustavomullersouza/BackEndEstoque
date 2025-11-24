package com.example.Estoque.Repository
// Define o pacote onde o repositório está localizado,
// ajudando na organização do projeto.


import com.example.Estoque.Produto.Produto
// Importa a entidade Produto, que será manipulada por este repositório.


import org.springframework.data.jpa.repository.JpaRepository
// Importa a interface JpaRepository, que fornece métodos prontos
// para operações no banco de dados (CRUD, paginação, consultas, etc).


import org.springframework.stereotype.Repository
// Indica ao Spring que esta interface é um componente de acesso a dados (DAO).


// Marca esta interface como um repositório JPA do Spring.
@Repository
interface ProdutoRepository : JpaRepository<Produto, Long>
// Cria uma interface que herda JpaRepository.

// JpaRepository<Produto, Long> significa:
// - Produto → tipo da entidade que este repositório vai gerenciar
// - Long → tipo do ID da entidade

// Como herda JpaRepository, essa interface já possui métodos prontos como:
// - findAll()
// - findById(id)
// - save(produto)
// - deleteById(id)
// - existsById(id)
// E muitos outros, sem precisar escrever código adicional.

