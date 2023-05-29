/*
O código fornecido é um exemplo de um controlador em uma aplicação Java com o
framework Spring Boot que implementa uma API REST com três rotas.

1. A anotação `@RestController` é usada para marcar a classe `HelloWorldController`
como um controlador REST, indicando que os métodos nessa classe irão retornar dados
que serão serializados como resposta HTTP.

2. O método `helloWorld()` é mapeado para a URL `/hello-world` e será invocado quando
uma solicitação HTTP GET for feita para essa URL. Ele retorna uma string "Hello World".

3. O método `helloWorldBean()` é mapeado para a URL `/hello-world-bean` e também será
invocado quando uma solicitação HTTP GET for feita para essa URL. Ele retorna um objeto
`HelloWorldBean` que contém a mensagem "Hello World".

4. O método `helloWorldPathVariable(@PathVariable String name)` é mapeado para a URL
`/hello-world/path-variable/{name}` e será invocado quando uma solicitação HTTP GET for
feita para essa URL com um valor no espaço reservado `{name}`. Ele retorna um objeto
`HelloWorldBean` com uma mensagem formatada contendo o valor fornecido na variável `name`.

5. A anotação `@PathVariable` é usada para indicar que o valor na URL é capturado e
atribuído ao parâmetro `name` do método.

Resumindo, o código define um controlador REST com três rotas. A primeira rota
`/hello-world` retorna uma string "Hello World" diretamente como resposta HTTP. A
segunda rota `/hello-world-bean` retorna um objeto `HelloWorldBean` serializado
em formato JSON contendo a mensagem "Hello World". A terceira rota
`/hello-world/path-variable/{name}` retorna um objeto `HelloWorldBean` com uma mensagem
formatada que inclui o valor fornecido na variável `name` na URL.
*/

package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
