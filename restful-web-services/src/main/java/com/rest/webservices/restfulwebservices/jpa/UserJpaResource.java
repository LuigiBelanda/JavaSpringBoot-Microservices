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
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Get /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

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
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException("id:"+id);

        return user.get().getPosts();
    }

    /*
    O método `createPostForUser` é responsável por criar um novo objeto `Post` associado a um usuário específico com base no ID fornecido na URL.

    - Ele é anotado com `@PostMapping("jpa/users/{id}/posts")`, indicando que é um endpoint que aceita requisições HTTP POST para criar um novo post para um usuário.
    - O parâmetro `@PathVariable int id` indica que o ID do usuário será extraído da URL.
    - O parâmetro `@Valid @RequestBody Post post` indica que o corpo da requisição será convertido em um objeto `Post` e validado com base nas anotações de validação presentes na classe `Post`.

    Aqui está o fluxo de execução do método:

    1. O método começa procurando o usuário com o ID fornecido usando o método `findById(id)` do repositório de
    usuários. Ele retorna um `Optional<User>` que pode conter o usuário encontrado ou estar vazio.

    2. Se o usuário não for encontrado (ou seja, `user.isEmpty()`), uma exceção `UserNotFoundException`
    é lançada, informando que o usuário com o ID fornecido não foi encontrado.

    3. Caso contrário, se o usuário for encontrado, é atribuído ao objeto `post` usando o método
    `post.setUser(user.get())`. Isso estabelece a relação many-to-one entre o post e o
    usuário, definindo o usuário como o autor do post.

    4. O post é então salvo no repositório de posts usando o método `postRepository.save(post)`, que retorna o
    objeto `Post` salvo com o ID gerado.

    5. Em seguida, é criada uma URI (Uniform Resource Identifier) para o recurso recém-criado usando
    `ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri()`.
    A URI inclui o ID do post salvo.

    6. Finalmente, uma resposta HTTP 201 (Created) é retornada com a URI do recurso recém-criado
    usando `ResponseEntity.created(location).build()`. Isso indica que a criação do post foi bem-sucedida
    e inclui o local onde o recurso pode ser acessado.
    */
    @PostMapping("jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException("id:"+id);

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
