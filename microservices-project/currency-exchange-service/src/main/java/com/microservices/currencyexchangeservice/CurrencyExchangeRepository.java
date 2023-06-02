/*
Nesse código, temos uma interface chamada `CurrencyExchangeRepository` que estende a interface
`JpaRepository` do Spring Data JPA.

A interface `JpaRepository` é uma interface genérica fornecida pelo Spring Data JPA que
define métodos comuns para realizar operações de persistência em um banco de dados para uma entidade específica.

No caso do `CurrencyExchangeRepository`, ele está associado à entidade `CurrencyExchange`.
Essa interface permite que operações de consulta, inserção, atualização e exclusão
sejam realizadas no banco de dados para objetos `CurrencyExchange`.

Além dos métodos herdados da interface `JpaRepository`, a interface `CurrencyExchangeRepository`
também declara um método personalizado chamado `findByFromAndTo`. Esse método permite buscar um
objeto `CurrencyExchange` no banco de dados com base nos valores dos campos `from` e `to`.
Ele retorna um objeto `CurrencyExchange` correspondente encontrado no banco de dados ou `null`
se nenhum registro correspondente for encontrado.

Ao utilizar a interface `CurrencyExchangeRepository`, você pode facilmente interagir com o
banco de dados, realizar consultas personalizadas e executar operações de CRUD (Create, Read, Update, Delete)
na entidade `CurrencyExchange`, sem a necessidade de escrever código SQL manualmente.
O Spring Data JPA automatiza a geração de consultas SQL com base nos métodos definidos na interface
e gerencia as operações de persistência para você.
*/

package com.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
