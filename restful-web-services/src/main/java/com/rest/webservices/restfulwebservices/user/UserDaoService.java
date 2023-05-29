/*
A classe `UserDaoService` é um componente do Spring Boot que simula uma camada de acesso
a dados para operações relacionadas a usuários. Vamos analisar o código e explicar suas funcionalidades:

1. A anotação `@Component` é usada para marcar a classe como um componente gerenciado
pelo Spring. Isso permite que a classe seja detectada e utilizada pelo mecanismo de
injeção de dependência do Spring.

2. A classe possui um campo estático chamado `users`, que é uma lista de objetos `User`.
Essa lista é usada para armazenar os usuários simulados.

3. No bloco estático inicializador, são criados alguns objetos `User` com IDs, nomes e
 datas de nascimento simulados. Esses usuários são adicionados à lista `users`. A data
 de nascimento é calculada subtraindo uma determinada quantidade de anos da data atual,
 usando o método `LocalDate.now().minusYears()`.

4. O método `findAll()` retorna a lista de usuários armazenada no `UserDaoService`. Esse
método é responsável por recuperar todos os usuários existentes.

5. O método `findOne(int id)` recebe um ID como parâmetro e retorna o usuário correspondente
a esse ID. Ele utiliza o método `findFirst()` para encontrar o primeiro usuário na lista
`users` que corresponda ao ID fornecido. A expressão lambda `user -> user.getId().equals(id)`
é usada para verificar se o ID do usuário é igual ao ID fornecido.

Resumindo, a classe `UserDaoService` simula uma camada de acesso a dados para operações
relacionadas a usuários. Ela mantém uma lista de usuários simulados e fornece métodos
para recuperar todos os usuários e encontrar um usuário específico com base no ID.
Essa classe é marcada como um componente do Spring para que possa ser gerenciada e
injetada em outras partes da aplicação, se necessário.
*/

package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }
}
