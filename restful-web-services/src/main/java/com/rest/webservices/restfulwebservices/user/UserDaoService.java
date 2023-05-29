/*
A classe `UserDaoService` é um componente Spring Boot que atua como uma camada de
acesso a dados simulada para operações relacionadas a usuários. Vamos analisar o
código e explicar suas funcionalidades:

1. A anotação `@Component` é usada para marcar a classe como um componente gerenciado
pelo Spring. Isso significa que o Spring irá gerenciar a criação e injeção de
dependências dessa classe.

2. A classe `UserDaoService` possui um campo estático `users`, que é uma lista de
objetos `User`. Essa lista é usada para armazenar os usuários simulados.

3. No bloco estático inicializador, são criados alguns objetos `User` e adicionados
à lista `users`. Esses usuários simulados possuem um ID, um nome e uma data de
nascimento que é calculada subtraindo-se uma determinada quantidade de anos da data atual.

4. O método `findAll()` retorna a lista de usuários armazenada no `UserDaoService`.
Esse método é responsável por recuperar todos os usuários existentes.

Resumindo, a classe `UserDaoService` simula um serviço de acesso a dados para
operações relacionadas a usuários. Ela mantém uma lista de usuários simulados
e fornece um método para recuperar todos os usuários. Essa classe é marcada
como um componente do Spring para que possa ser gerenciada e injetada em outras
partes da aplicação, se necessário.
*/

package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(3, "Jim", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }
}
