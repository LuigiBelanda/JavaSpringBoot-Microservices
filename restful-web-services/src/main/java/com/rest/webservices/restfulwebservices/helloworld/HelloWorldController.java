/*
O código fornecido é um exemplo de um controlador em uma
aplicação Java com o framework Spring Boot que implementa uma
API REST simples com duas rotas.

1. A anotação `@RestController` é usada para marcar a classe
`HelloWorldController` como um controlador REST, indicando que os métodos
nessa classe irão retornar dados que serão serializados como resposta HTTP.

2. A classe `HelloWorldController` contém dois métodos mapeados para
diferentes URLs usando a anotação `@GetMapping`.

3. O método `helloWorld()` é mapeado para a URL `/hello-world` e será
invocado quando uma solicitação HTTP GET for feita para essa URL. Ele retorna uma string "Hello World".

4. O método `helloWorldBean()` é mapeado para a URL `/hello-world-bean`
e também será invocado quando uma solicitação HTTP GET for feita para essa
URL. Ele retorna um objeto `HelloWorldBean` que contém uma mensagem "Hello World".

5. A classe `HelloWorldBean` é uma classe simples que representa um objeto
 com uma mensagem. Neste exemplo, ele possui um construtor que recebe a
  mensagem como parâmetro e um método getter para obter a mensagem.

Resumindo, o código define um controlador REST que responde a duas solicitações
GET em diferentes URLs. Uma rota `/hello-world` retorna uma string "Hello World"
diretamente como resposta HTTP, enquanto a rota `/hello-world-bean` retorna um objeto
`HelloWorldBean` serializado em formato JSON contendo a mensagem "Hello World".
*/

package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {
    // hello-world
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
}
