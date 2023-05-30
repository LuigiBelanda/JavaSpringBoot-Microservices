/*
O trecho de código fornecido mostra uma classe chamada
`CustomizeResponseEntityExceptionHandler` que lida com exceções personalizadas
em uma aplicação RESTful. Vamos analisar o código e explicar suas funcionalidades:

1. A anotação `@ControllerAdvice` indica que a classe é usada para aconselhar todos os
controladores na aplicação. Ela permite centralizar o tratamento de exceções em um local específico.

2. A classe estende `ResponseEntityExceptionHandler`, que é uma classe fornecida pelo
Spring que fornece um tratamento padrão para exceções relacionadas a respostas HTTP.

3. O método `handleAllException(Exception ex, WebRequest request)` é anotado com
`@ExceptionHandler(Exception.class)` e é responsável por tratar todas as exceções
não tratadas na aplicação. Ele recebe a exceção `Exception` e o objeto `WebRequest`
como parâmetros.

4. Dentro desse método, é criado um objeto `ErrorDetails`, que contém informações
sobre o erro, como a data e hora em que ocorreu, a mensagem de erro e a descrição da solicitação.

5. Em seguida, é retornada uma instância de `ResponseEntity<ErrorDetails>`, que
representa a resposta HTTP com o corpo contendo os detalhes do erro. No caso de
exceções gerais (`Exception`), é retornado um código de status HTTP 500 (INTERNAL_SERVER_ERROR).

6. O método `handleUserNotFoundException(Exception ex, WebRequest request)` é
anotado com `@ExceptionHandler(UserNotFoundException.class)` e é responsável por
tratar a exceção `UserNotFoundException`, que é uma exceção personalizada definida anteriormente.

7. Dentro desse método, também é criado um objeto `ErrorDetails` com as informações do erro.

8. É retornado uma instância de `ResponseEntity<ErrorDetails>` com um código de
status HTTP 404 (NOT_FOUND), indicando que o recurso solicitado não foi encontrado.

Resumindo, a classe `CustomizeResponseEntityExceptionHandler` é um controlador de
exceções personalizadas em uma aplicação RESTful. Ela estende `ResponseEntityExceptionHandler`
e fornece métodos para tratar exceções gerais e uma exceção personalizada (`UserNotFoundException`).
Esses métodos constroem um objeto `ErrorDetails` com informações sobre o erro e retornam uma
resposta HTTP com o corpo contendo os detalhes do erro e o código de status apropriado.
*/

package com.rest.webservices.restfulwebservices.exception;

import com.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
