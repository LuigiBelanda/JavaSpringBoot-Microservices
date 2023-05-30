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

import jakarta.validation.Valid;
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
        User user = service.findOne(id);

        // Se o usuário não for encontrado (ou seja, se o valor retornado for null),
        // uma exceção UserNotFoundException é lançada. Essa exceção personalizada indica
        // que o usuário com o ID fornecido não foi encontrado.
        // A mensagem de exceção é construída concatenando a string "id:" com o valor do ID.
        if (user == null) throw new UserNotFoundException("id:" + id);

        return user;
    }

    /*
    O trecho de código fornecido é um exemplo de método `createUser`
    mapeado para a rota POST `/users` em uma API REST. Vamos analisar o código passo a passo:

    1. A anotação `@PostMapping` indica que esse método será acionado
    quando uma solicitação HTTP POST for feita para a rota `/users`.

    2. A anotação `@Valid` é usada para habilitar a validação do objeto
    `User` enviado no corpo da solicitação. Isso significa que as anotações
    de validação definidas na classe `User` serão aplicadas ao objeto recebido
    para garantir que ele atenda aos critérios de validação especificados.

    3. O parâmetro `@RequestBody User user` indica que o objeto `User` deve
    ser obtido a partir do corpo da solicitação HTTP e vinculado ao parâmetro
    `user` do método. O objeto `user` representa os dados do usuário que serão criados.

    4. O método chama o método `save` do serviço (`service.save(user)`)
    para salvar o objeto `User` no banco de dados ou em outra camada de
    armazenamento. A implementação específica do método `save` dependerá do contexto da aplicação.

    5. Em seguida, é criado um objeto `URI` chamado `location`. Esse objeto
    é construído usando a classe `ServletUriComponentsBuilder`, que é uma
    classe auxiliar do Spring Framework para construir URIs com base na
    solicitação atual. A URI é construída adicionando o valor do ID do usuário
    salvo (`savedUser.getId()`) à rota atual (`fromCurrentRequest().path("/{id}")`).

    6. Por fim, é retornado um objeto `ResponseEntity` com o código de status 201
    (Created) e o cabeçalho `Location` definido para a URI do recurso criado.
    A chamada ao método `created(location)` cria uma resposta HTTP 201 com o
    cabeçalho `Location` definido para a URI do recurso criado.

    Em resumo, o método `createUser` recebe um objeto `User` do corpo da
    solicitação, o salva no banco de dados e retorna uma resposta HTTP 201
    com o cabeçalho `Location` definido para a URI do recurso criado.
    */
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedUser.getId())
                            .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }
}
