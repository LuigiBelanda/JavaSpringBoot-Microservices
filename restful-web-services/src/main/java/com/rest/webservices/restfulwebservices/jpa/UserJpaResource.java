/*
Esta classe é um controlador RESTful chamado `UserJpaResource` que manipula as operações CRUD (create, read, update, delete) para a entidade `User` usando o Spring Data JPA. Vamos explicar cada método desta classe:

- `@RestController`: Essa anotação indica que a classe é um controlador RESTful
que lida com as requisições HTTP.

- `private UserRepository repository`: É uma instância da interface `UserRepository`
que é injetada no construtor da classe. Essa instância é usada para interagir com o banco de dados.

- `public UserJpaResource(UserRepository repository)`: É o construtor da classe
`UserJpaResource` que recebe uma instância de `UserRepository` e a atribui ao campo `repository`.

- `@GetMapping("/jpa/users")`: Essa anotação mapeia a URL `/jpa/users` para o método
`retrieveAllUsers()`. Quando uma requisição GET é feita para essa URL, o método é executado.

- `public List<User> retrieveAllUsers()`: É o método que retorna todos os usuários do
banco de dados. Ele chama o método `findAll()` do repositório para obter a lista de usuários.

- `@GetMapping("/jpa/users/{id}")`: Essa anotação mapeia a URL `/jpa/users/{id}` para o
método `retrieveUser()`. O `{id}` é um espaço reservado para o identificador do usuário na URL.

- `public EntityModel<User> retrieveUser(@PathVariable int id)`: É o método que retorna os
detalhes de um usuário específico com o ID fornecido. Ele chama o método `findById()` do
repositório para buscar o usuário no banco de dados. Se o usuário não for encontrado, é
lançada uma exceção `UserNotFoundException`. Caso contrário, os detalhes do usuário são
encapsulados em um objeto `EntityModel`, e um link para todos os usuários é adicionado a ele.

- `@PostMapping("jpa/users")`: Essa anotação mapeia a URL `/jpa/users` para o método
`createUser()`. Quando uma requisição POST é feita para essa URL, o método é executado.

- `public ResponseEntity<Object> createUser(@Valid @RequestBody User user)`: É o método
que cria um novo usuário. Ele recebe um objeto `User` no corpo da requisição, que é
validado usando a anotação `@Valid`. O usuário é salvo no banco de dados usando o método
`save()` do repositório. Em seguida, é construída uma URI para o recurso recém-criado e
retornada na resposta HTTP com o status `201 Created`.

- `@DeleteMapping("jpa/users/{id}")`: Essa anotação mapeia a URL `/jpa/users/{id}` para o
método `deleteUser()`. O `{id}` é um espaço reservado para o identificador do usuário na URL.

- `public void deleteUser(@PathVariable int id)`: É o método que exclui um usuário com o
ID fornecido do banco de dados. Ele chama o método `deleteById()` do repositório para realizar a exclusão.

Essa classe implementa as operações básicas de um serviço RESTful para a entidade `User`,
permitindo recuperar todos os usuários, recuperar um usuário específico, criar um novo
usuário e excluir um usuário existente.
*/

package com.rest.webservices.restfulwebservices.jpa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.rest.webservices.restfulwebservices.user.Post;
import com.rest.webservices.restfulwebservices.user.User;
import com.rest.webservices.restfulwebservices.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {
    private UserRepository repository;

    public UserJpaResource(UserRepository repository) {
        this.repository = repository;
    }

    // Get /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        // Se o usuário não for encontrado (ou seja, se o valor retornado for null),
        // uma exceção UserNotFoundException é lançada. Essa exceção personalizada indica
        // que o usuário com o ID fornecido não foi encontrado.
        // A mensagem de exceção é construída concatenando a string "id:" com o valor do ID.
        if (user.isEmpty()) throw new UserNotFoundException("id:" + id);

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException("id:"+id);

        return user.get().getPosts();
    }
}
