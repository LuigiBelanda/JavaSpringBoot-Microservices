package com.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    /*
    O Environment é uma classe fornecida pelo Spring Framework que permite acessar as propriedades
    de configuração do ambiente de execução.

    Em seguida, é obtido o valor da propriedade "local.server.port" do objeto Environment, que representa a porta
    na qual o aplicativo está sendo executado. Esse valor é adicionado à instância de CurrencyExchange
    por meio do método setEnvironment.
    */
    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from , to, BigDecimal.valueOf(50));

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
