package com.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    /*
    Nesse trecho de código, temos um método `retrieveExchangeValue` mapeado para o endpoint
    `/currency-exchange/from/{from}/to/{to}` utilizando a anotação `@GetMapping`.
    Isso significa que esse método será executado quando houver uma requisição HTTP GET para
    esse endpoint, onde `{from}` e `{to}` são variáveis de caminho (path variables) que serão extraídas da URL.

    Dentro do método, a primeira ação realizada é chamar o método `findByFromAndTo` do
    repositório `repository` passando os valores das variáveis `from` e `to`. Esse método
    é responsável por buscar no banco de dados uma instância da entidade `CurrencyExchange`
    que corresponda aos valores especificados.

    Em seguida, é feita uma verificação para garantir que um objeto `CurrencyExchange`
    tenha sido encontrado. Caso o objeto seja nulo, é lançada uma exceção `RuntimeException`
    com uma mensagem informando que os dados para a conversão especificada não foram encontrados.

    Após isso, é obtido o valor da propriedade `local.server.port` do objeto `environment`,
    que contém informações do ambiente em que a aplicação está sendo executada.
    Esse valor do número da porta é utilizado para definir o ambiente no objeto `CurrencyExchange` encontrado.

    Por fim, o objeto `CurrencyExchange` é retornado como resposta da requisição, contendo as
    informações da conversão e o ambiente definido.
    */
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

        if (currencyExchange == null) {
            throw new RuntimeException("unable to find data for " + from + " to " + to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
