/*
Nesse trecho de código, temos uma interface chamada `CurrencyExchangeProxy` que
utiliza a anotação `@FeignClient`. Essa anotação faz parte do Spring Cloud OpenFeign,
que é uma biblioteca utilizada para criar clientes de serviços REST de forma declarativa.

A anotação `@FeignClient` é usada para definir um cliente Feign, que é responsável por
se comunicar com um serviço remoto. Nesse caso, o cliente Feign é usado para se comunicar
com o serviço "currency-exchange".

A anotação `@FeignClient` possui dois parâmetros:

- `name`: Especifica o nome do cliente Feign. Nesse caso, o valor é "currency-exchange".
- `url`: Especifica a URL base do serviço remoto. Nesse caso, a URL base é "localhost:8000".

Dentro da interface, temos um método chamado `retrieveExchangeValue` que é anotado com
`@GetMapping`. Essa anotação indica que esse método será usado para realizar uma requisição
HTTP GET para o endpoint especificado.

O endpoint é definido como `/currency-exchange/from/{from}/to/{to}`, onde `{from}` e `{to}`
são variáveis de caminho (path variables) que serão substituídas pelos valores fornecidos no
momento da chamada do método.

O retorno desse método é do tipo `CurrencyConversion`, que é a classe que representa a resposta
da chamada ao serviço "currency-exchange".

Em resumo, essa interface `CurrencyExchangeProxy` define um cliente Feign para se comunicar com
o serviço "currency-exchange". O método `retrieveExchangeValue` será usado para buscar as informações
de conversão de moeda a partir do serviço remoto. Essa interface facilita a comunicação com o serviço
remoto de forma declarativa, sem a necessidade de escrever o código manualmente para realizar as
requisições HTTP.
*/

package com.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

// @FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
