/*
Este é um exemplo de uma interface de repositório JPA (Java Persistence API) chamada `UserRepository`. Vamos explicar cada parte dessa interface:

- `import com.rest.webservices.restfulwebservices.user.User;`: Essa linha importa a classe `User`
que será usada como entidade no repositório.

- `import org.springframework.data.jpa.repository.JpaRepository;`: Essa linha importa a interface
`JpaRepository` fornecida pelo Spring Data JPA. Essa interface fornece métodos para realizar
operações de persistência em entidades JPA, como inserir, atualizar, excluir e buscar registros
no banco de dados.

- `public interface UserRepository extends JpaRepository<User, Integer> {`: Essa declaração
define a interface `UserRepository` que estende a interface `JpaRepository`. Ao estender
`JpaRepository`, a interface `UserRepository` herda os métodos de CRUD (create, read, update,
delete) fornecidos pelo Spring Data JPA.

  - `User`: É o tipo da entidade que será gerenciada pelo repositório, ou seja, a classe `User`.

  - `Integer`: É o tipo do identificador da entidade, ou seja, o tipo do campo `id` na classe `User`.

Portanto, ao usar a interface `UserRepository`, você terá acesso a métodos como `save()`,
`delete()`, `findById()`, `findAll()`, entre outros, que podem ser usados para realizar
operações de persistência no banco de dados relacionadas à entidade `User`. Essa interface
permite interagir facilmente com o banco de dados sem a necessidade de escrever consultas SQL manualmente.
*/

package com.rest.webservices.restfulwebservices.jpa;

import com.rest.webservices.restfulwebservices.user.Post;
import com.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
