/*
O código fornecido é uma classe chamada `UserResource` que atua como um controlador REST
para a entidade `User`. Vamos analisar o código e explicar suas funcionalidades:

1. A classe `UserResource` é anotada com `@RestController`, indicando que é um controlador
REST que trata solicitações HTTP.

2. A classe possui um campo `service` do tipo `UserDaoService`, que é injetado por meio do
construtor. Isso permite que o controlador utilize os serviços fornecidos pela classe
`UserDaoService` para acessar e manipular os dados dos usuários.

3. O método `retrieveAllUsers()` é mapeado para a URL `/users` e será invocado quando uma
solicitação HTTP GET for feita para essa URL. Ele chama o método `findAll()` do serviço
`UserDaoService` para obter uma lista de todos os usuários e retorna essa lista como resposta HTTP.

4. O método `retrieveUser(@PathVariable int id)` é mapeado para a URL `/users/{id}` e será
invocado quando uma solicitação HTTP GET for feita para essa URL com um valor no espaço
reservado `{id}`. Ele chama o método `findOne(id)` do serviço `UserDaoService` para obter
o usuário com o ID fornecido e retorna esse usuário como resposta HTTP.

5. A anotação `@PathVariable` é usada para indicar que o valor na URL é capturado e
atribuído ao parâmetro `id` do método.

Resumindo, a classe `UserResource` é um controlador REST que trata as solicitações
HTTP relacionadas aos usuários. Ela utiliza o serviço `UserDaoService` para acessar
e manipular os dados dos usuários. Os métodos mapeados respondem a solicitações GET
em URLs específicas, retornando listas de usuários ou usuários individuais, dependendo do caso.
*/

package com.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {
    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    // Get /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // Get /users/pathVariable
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        service.save(user);
    }
}
