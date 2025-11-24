package com.example.Estoque
// Define o pacote principal da aplicação. Geralmente é o pacote raiz,
// e todas as outras classes ficam dentro dele ou em subpacotes.


import org.springframework.boot.autoconfigure.SpringBootApplication
// Importa a anotação que configura automaticamente vários recursos do Spring Boot,
// como: varredura de componentes, configuração do Spring, inicialização do servidor embutido etc.


import org.springframework.boot.runApplication
// Importa a função que inicia a aplicação Spring Boot quando chamada.



// Marca esta classe como a classe principal do Spring Boot.
// @SpringBootApplication combina três anotações importantes:
// - @Configuration → permite declarar beans
// - @EnableAutoConfiguration → ativa a configuração automática do Spring
// - @ComponentScan → faz o Spring procurar componentes no projeto
@SpringBootApplication
class EstoqueApplication
// Classe vazia utilizada apenas como ponto de entrada da aplicação.
// O Spring Boot usa ela como referência para carregar o contexto da aplicação.



// Função principal: é o ponto de entrada do programa (como em qualquer aplicação Kotlin/Java).
fun main(args: Array<String>) {

    // runApplication inicializa a aplicação Spring Boot.
    // - EstoqueApplication → classe principal anotada com @SpringBootApplication
    // - *args → passa argumentos de execução, caso existam.
    runApplication<EstoqueApplication>(*args)
}
