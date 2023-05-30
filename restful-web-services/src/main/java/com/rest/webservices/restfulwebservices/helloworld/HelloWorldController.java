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

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

// REST API
@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    /*
    O método `helloWorldInternationalized()` é um endpoint GET que retorna uma mensagem internacionalizada.

    Vamos analisar o código passo a passo:

    1. `Locale locale = LocaleContextHolder.getLocale();`: A classe `LocaleContextHolder` é
    usada para obter o `Locale` atual da solicitação. O `Locale` representa a localização
    ou idioma preferido do cliente que fez a solicitação.

    2. `messageSource.getMessage("good.morning.message", null, "Default message", locale)`: O
    `messageSource` é um bean do Spring Framework que gerencia as mensagens internacionalizadas.
    O método `getMessage()` é usado para obter a mensagem internacionalizada com base na chave fornecida.

       - O primeiro parâmetro `"good.morning.message"` é a chave da mensagem, que é usada para identificar a mensagem no arquivo de propriedades de mensagens internacionalizadas.
       - O segundo parâmetro `null` é um array de argumentos opcionais que podem ser usados para substituir valores dinâmicos na mensagem.
       - O terceiro parâmetro `"Default message"` é uma mensagem padrão que será retornada se a chave da mensagem não for encontrada.
       - O último parâmetro `locale` indica o `Locale` a ser usado para recuperar a mensagem correta para o idioma preferido do cliente.

       Em resumo, esse código obtém o `Locale` atual da solicitação e usa o `messageSource`
       para obter a mensagem internacionalizada com base na chave fornecida. A mensagem
       é retornada como resposta HTTP para o cliente que fez a solicitação.
    */
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
    }
}
