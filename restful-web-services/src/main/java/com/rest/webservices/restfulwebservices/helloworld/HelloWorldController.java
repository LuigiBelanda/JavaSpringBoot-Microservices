/*
O código fornecido é um exemplo de um controlador em uma aplicação Java com o framework Spring Boot,
que implementa uma API REST simples. Vamos analisar o código passo a passo:

1. A primeira linha de código importa as dependências necessárias para o controlador.

2. A anotação `@RestController` é usada para marcar a classe `HelloWorldController`
como um controlador REST. Essa anotação combina as anotações `@Controller` e `@ResponseBody`,
indicando que os métodos nessa classe irão retornar diretamente dados que serão serializados como resposta HTTP.

3. O método `helloWorld()` é mapeado para a URL `/hello-world` e será invocado quando uma
solicitação HTTP GET for feita para essa URL.

4. A anotação @RequestMapping foi substituída pela anotação @GetMapping.
Ambas as anotações são usadas para mapear um método para uma URL específica,
mas a @GetMapping é uma forma mais específica da @RequestMapping para mapear
solicitações HTTP GET. Com isso, não é mais necessário especificar o método HTTP na anotação.

A anotação @RequestMapping(method = RequestMethod.GET) foi
substituída apenas por @GetMapping. Essa mudança torna o código mais simples e legível

5. O método `helloWorld()` retorna a string "Hello World". Essa string será serializada e
enviada como resposta HTTP para o cliente que fez a solicitação.

Resumindo, o código define um controlador REST simples que responde a uma solicitação GET
na URL `/hello-world` com a string "Hello World". Quando essa URL é acessada, o método `helloWorld()`
é chamado e a string é retornada como resposta HTTP.
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
}
