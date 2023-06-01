package com.microservices.currencyconversionservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {
    /*
    Nesse trecho de código, temos um método `calculateCurrencyConversion` mapeado para o endpoint
    `/currency-conversion/from/{from}/to/{to}/quantity/{quantity}` utilizando a anotação `@GetMapping`.
    Isso significa que esse método será executado quando houver uma requisição HTTP GET para esse endpoint,
    onde `{from}`, `{to}` e `{quantity}` são variáveis de caminho (path variables) que serão extraídas da URL.

    Dentro do método, é criado um objeto `HashMap` chamado `uriVariables` para armazenar as variáveis de
    caminho `{from}` e `{to}`. Em seguida, é feita uma chamada GET para o serviço `currency-exchange` usando
    o `RestTemplate`. O URL da requisição é construído com base na URL base
    `http://localhost:8000/currency-exchange/from/{from}/to/{to}` e as variáveis de caminho são substituídas
    pelos valores contidos no `uriVariables`. O tipo de resposta esperado é `CurrencyConversion.class`.

    A resposta da requisição é armazenada em um objeto `ResponseEntity<CurrencyConversion>`. Esse objeto
    contém informações como o status da resposta, os cabeçalhos e o corpo da resposta. No caso, o corpo da
    resposta é do tipo `CurrencyConversion`, pois é o tipo esperado definido na chamada GET.

    Em seguida, o objeto `CurrencyConversion` é obtido do corpo da resposta usando o método `getBody()`
    do `ResponseEntity`.

    Por fim, um novo objeto `CurrencyConversion` é criado com base nos valores obtidos. Esse objeto contém
    informações da conversão de moeda, como o ID, as moedas de origem e destino, a quantidade convertida,
    o múltiplo de conversão, o resultado da conversão e o ambiente. Esse objeto é retornado como resposta da requisição.

    Em resumo, esse método realiza uma chamada GET para o serviço `currency-exchange` para obter as
    informações de conversão de moeda, calcula a conversão com base na quantidade fornecida e retorna
    o resultado.
    */
    @GetMapping("/currency-conversio/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> responseEntity =  new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables
        );

        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment()
        );
    }
}
