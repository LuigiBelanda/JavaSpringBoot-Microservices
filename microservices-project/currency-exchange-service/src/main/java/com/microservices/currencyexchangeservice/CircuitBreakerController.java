/*
Vamos analisar o código do `CircuitBreakerController` passo a passo:

1. A classe é anotada com `@RestController`, indicando que é um controlador que lida com requisições HTTP e
retorna respostas.

2. É criado um objeto `Logger` para realizar o registro de logs, capturando a classe `CircuitBreakerController`
como parâmetro.

3. O método `sampleApi()` é anotado com `@GetMapping`, indicando que ele responde a requisições HTTP GET para a
URL raiz.

4. A anotação `@Retry` é aplicada ao método `sampleApi()`. Isso indica que a operação será retried (tentada
novamente) em caso de falha, seguindo uma configuração pré-definida.

5. Dentro do método `sampleApi()`, é registrado um log informando que uma chamada à API de exemplo foi recebida.

6. Em seguida, é feita uma chamada HTTP GET para a URL "http://localhost:8080/some-dummy-url" usando o
`RestTemplate`. O resultado da chamada é armazenado em um objeto `ResponseEntity<String>`.

7. O corpo da resposta é retornado como resultado do método.

8. O método `hardCodedResponse()` é definido como um fallback method para o caso de falha na chamada da
API. Ele recebe um parâmetro `Exception` e retorna uma resposta "fallback-response" fixa.

Em resumo, o `CircuitBreakerController` é um controlador que lida com requisições HTTP GET para a URL raiz.
Ele usa o `RestTemplate` para fazer uma chamada a uma URL de exemplo e retorna o corpo da resposta.
Caso ocorra uma falha na chamada, o fallback method `hardCodedResponse()` é acionado e retorna uma resposta
alternativa. O controlador também registra logs das chamadas recebidas. A anotação `@Retry`
adiciona a funcionalidade de retry na operação, seguindo a configuração definida.
*/

package com.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping
    // @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
    // @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
    // @RateLimiter(name = "default")
    @Bulkhead(name = "sample-api")
    public String sampleApi() {
        logger.info("Sample API call received");

        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);

        return forEntity.getBody();
    }

    public String hardCodedResponse(Exception ex) {
        return "fallback-response";
    }
}
