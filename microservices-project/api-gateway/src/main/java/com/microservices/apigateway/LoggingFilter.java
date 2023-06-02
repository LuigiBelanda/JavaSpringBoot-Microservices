/*
Vamos analisar o código passo a passo:

1. A anotação `@Component` é usada para marcar a classe `LoggingFilter` como um componente gerenciado pelo Spring.

2. A classe `LoggingFilter` implementa a interface `GlobalFilter`, que é fornecida pelo Spring Cloud
Gateway. Isso permite que a classe atue como um filtro global para todas as solicitações que passam pelo gateway.

3. A classe declara um objeto `Logger` chamado `logger`, que é usado para registrar mensagens de log.
O logger é obtido chamando o método `getLogger` da classe `LoggerFactory` e passando a classe
`LoggingFilter.class` como argumento.

4. A classe substitui o método `filter` da interface `GlobalFilter`. Esse método é chamado para cada
solicitação recebida pelo gateway e é onde a lógica do filtro é implementada.

5. Dentro do método `filter`, a linha `logger.info("Path of the request received -> {}",
exchange.getRequest().getPath());` registra uma mensagem de log. A mensagem exibe o caminho da
solicitação recebida usando `exchange.getRequest().getPath()`. O método `info` do logger é
usado para registrar a mensagem com nível de log "info".

6. Após o registro da mensagem de log, o método `filter` chama `chain.filter(exchange)` para
permitir que a solicitação continue seu processamento normalmente pelo gateway. Essa chamada
encaminha a solicitação para o próximo filtro na cadeia ou para o roteamento final, dependendo
da configuração do gateway.

Em resumo, o filtro `LoggingFilter` captura todas as solicitações que passam pelo Spring Cloud
Gateway e registra uma mensagem de log com o caminho da solicitação. Isso pode ser útil para fins
de auditoria, depuração ou acompanhamento do fluxo de solicitações no gateway.
*/

package com.microservices.apigateway;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class LoggingFilter implements GlobalFilter {
    private Logger logger = (Logger) LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // logger.info("Path of the request received -> {}", exchange.getRequest().getPath());
        logger.info("Path of the request received -> {}");

        return chain.filter(exchange);
    }
}
