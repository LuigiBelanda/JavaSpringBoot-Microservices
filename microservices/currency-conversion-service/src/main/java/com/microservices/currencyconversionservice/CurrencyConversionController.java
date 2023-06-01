package com.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeProxy proxy;

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
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
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
                currencyConversion.getEnvironment() + "" + "rest template"
        );
    }

    /*
    Nesse trecho de código, temos um método chamado `calculateCurrencyConversionFeign`, que é
    mapeado para a rota "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}"
    usando a anotação `@GetMapping`. Esse método utiliza o cliente Feign chamado `proxy` para
    buscar as informações de conversão de moeda a partir do serviço "currency-exchange".

    O método recebe três variáveis de caminho (`from`, `to` e `quantity`), que são extraídas da
    URL e usadas como parâmetros do método.

    Dentro do método, é feita uma chamada ao método `retrieveExchangeValue` do cliente Feign `proxy`,
    passando as variáveis `from` e `to` como argumentos. Essa chamada faz uma requisição
    HTTP GET para o endpoint definido no cliente Feign, que por sua vez se comunica com o
    serviço "currency-exchange" para buscar as informações de conversão de moeda.

    O resultado dessa chamada é armazenado na variável `currencyConversion`, que representa
    a resposta da chamada ao serviço remoto.

    Em seguida, é criado um novo objeto `CurrencyConversion` com base nos dados obtidos. Esse
    objeto é retornado como resultado do método.

    No objeto `CurrencyConversion` retornado, os campos `id`, `from`, `to`, `quantity` e
    `conversionMultiple` são preenchidos com os valores correspondentes da resposta do
    serviço remoto. Além disso, o campo `totalCalculatedAmount` é calculado multiplicando
    a quantidade (`quantity`) pelo valor da conversão (`conversionMultiple`).

    Por fim, é adicionada a string "feign" ao campo `environment` do objeto `CurrencyConversion`,
    concatenando-a com o valor obtido do serviço remoto. Isso serve para indicar que a conversão
    foi feita utilizando o cliente Feign.

    Resumindo, o método `calculateCurrencyConversionFeign` utiliza o cliente Feign para buscar
    as informações de conversão de moeda do serviço "currency-exchange" e retorna um objeto
    `CurrencyConversion` com os valores calculados. O uso do cliente Feign simplifica a
    comunicação com o serviço remoto, permitindo que a lógica de chamada e tratamento da
    resposta seja encapsulada em uma interface declarativa.
    */
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + "" + "feign"
        );
    }
}
