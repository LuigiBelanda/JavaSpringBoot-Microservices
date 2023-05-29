/*
A classe `UserNotFoundException` é uma exceção personalizada que
 estende a classe `RuntimeException` no contexto de uma aplicação RESTful.
 Vamos analisar o código e explicar suas funcionalidades:

1. A anotação `@ResponseStatus(code = HttpStatus.NOT_FOUND)`
é usada para especificar o código de status HTTP que será retornado
quando essa exceção for lançada. Nesse caso, o código de status é 404
(Not Found), indicando que o recurso solicitado não foi encontrado.

2. A classe `UserNotFoundException` possui um construtor que recebe
uma mensagem como parâmetro. Essa mensagem é passada para o construtor
da classe pai `RuntimeException`, que trata das funcionalidades básicas de uma exceção.

Resumindo, a classe `UserNotFoundException` é uma exceção personalizada
que é lançada quando um usuário não é encontrado em uma operação relacionada
a usuários. Ela estende `RuntimeException` e é anotada com `@ResponseStatus`
para especificar o código de status HTTP 404 (Not Found) que será retornado
ao lançar essa exceção. Essa exceção pode ser usada para tratar situações
em que um usuário não existe ou não pode ser encontrado na aplicação RESTful.
*/

package com.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
