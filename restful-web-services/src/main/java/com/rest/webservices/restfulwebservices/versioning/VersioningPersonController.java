/*
A classe `VersioningPersonController` é um controlador Spring Boot que implementa a versão de
API baseada em URL. Vamos analisar o código passo a passo:

1. `@RestController`: Essa anotação marca a classe `VersioningPersonController` como um
controlador REST, indicando que os métodos nessa classe irão responder a solicitações HTTP.

2. `@GetMapping("/v1/person")`: Essa anotação mapeia o método `getFirstVersionOfPerson()`
para a URL "/v1/person". Isso significa que quando uma solicitação GET é feita para "/v1/person",
esse método será invocado.

3. O método `getFirstVersionOfPerson()` retorna um objeto `PersonV1`. Essa versão de pessoa
tem apenas um atributo de nome representado como uma única string.

4. `@GetMapping("/v2/person")`: Essa anotação mapeia o método `getSecondVersionOfPerson()`
para a URL "/v2/person". Isso significa que quando uma solicitação GET é feita para "/v2/person",
esse método será invocado.

5. O método `getSecondVersionOfPerson()` retorna um objeto `PersonV2`. Essa versão de pessoa
possui um atributo de nome representado como um objeto `Name`, que contém os atributos "firstName"
e "lastName".

Em resumo, o controlador `VersioningPersonController` define dois endpoints que correspondem a
diferentes versões da API. A versão 1 retorna uma pessoa com um nome em formato de string simples,
enquanto a versão 2 retorna uma pessoa com um nome representado como um objeto `Name` com atributos
separados para o primeiro nome e o sobrenome. Isso permite que os clientes da API acessem a versão
desejada especificando a versão desejada na URL da solicitação.
*/

package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
