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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    /*
    O trecho de código fornecido é um método chamado `createUser` em um controlador REST
    que trata solicitações HTTP POST para criar um novo usuário. Vamos analisar o código
     e explicar suas funcionalidades:

    1. A anotação `@PostMapping("/users")` é usada para mapear o método para a URL
    "/users" e especificar que ele deve ser invocado quando uma solicitação
    HTTP POST for feita para essa URL.

    2. O parâmetro `@RequestBody User user` indica que o corpo da solicitação HTTP
    deve ser convertido em um objeto do tipo `User`. O Spring irá realizar
    automaticamente essa conversão, baseado na estrutura e conteúdo do corpo da solicitação.

    3. O método `service.save(user)` é chamado para salvar o objeto `user` recebido
    no banco de dados ou em algum outro local de armazenamento.
    O detalhe da implementação do método `save` não é fornecido,
    mas geralmente ele seria responsável por persistir o objeto `user` e
    retornar o usuário salvo com um ID atribuído.

    4. A variável `location` é criada usando a classe `ServletUriComponentsBuilder`
    para construir a URI de resposta que aponta para o recurso recém-criado.
    A URI é construída a partir da URL atual da solicitação (`fromCurrentRequest()`),
    acrescentando o ID do usuário salvo na URL (`path("/{id}").buildAndExpand(savedUser.getId())`).

    5. A resposta é construída usando a classe `ResponseEntity`.
    O método `created(location)` é chamado para criar uma resposta com o
    status HTTP 201 (Created) e o cabeçalho `Location` definido com a URI do
    recurso criado. Em seguida, `build()` é chamado para criar a resposta final
    a ser enviada ao cliente.

    Resumindo, o método `createUser` é responsável por tratar solicitações
    HTTP POST para criar um novo usuário. Ele recebe um objeto `User` no
    corpo da solicitação, salva o usuário no banco de dados ou em algum
    outro local de armazenamento, e retorna uma resposta HTTP com o status
    201 (Created) e o cabeçalho `Location` apontando para a URI do usuário recém-criado.
    */
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedUser.getId())
                            .toUri();
        return ResponseEntity.created(location).build();
    }
}
