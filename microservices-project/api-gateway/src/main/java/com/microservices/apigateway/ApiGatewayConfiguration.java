/*
O código apresentado configura um roteador de gateway usando o Spring Cloud Gateway.
Vamos explicar passo a passo o que cada parte do código faz:

1. A anotação `@Bean` indica que o método `gatewayRouter` é um bean gerenciado pelo Spring e que
ele retorna um objeto do tipo `RouteLocator`, que é uma interface usada para definir rotas no gateway.

2. O método `gatewayRouter` recebe um objeto `RouteLocatorBuilder` como parâmetro. Esse objeto é
usado para construir as rotas do gateway.

3. O método `routes()` é chamado no `RouteLocatorBuilder` para iniciar a configuração das rotas do gateway.

4. Em seguida, várias rotas são definidas usando o método `route()`. Cada chamada ao método `route()`
define uma rota específica com base no caminho da URL.

5. A primeira rota é definida com o caminho `/get`. A função `filters()` é usada para adicionar filtros a essa rota. No exemplo, são adicionados dois filtros:
   - `addRequestHeader("MyHeader", "MyURI")` adiciona um cabeçalho HTTP à solicitação com o nome "MyHeader" e o valor "MyURI".
   - `addRequestParameter("Param", "MyValue")` adiciona um parâmetro de consulta à solicitação com o nome "Param" e o valor "MyValue".
   A rota é direcionada para a URI `http://httpbin.org:80`, que é o destino final da rota.

6. As próximas três rotas são definidas com os caminhos `/currency-exchange/**`, `/currency-conversion/**` e `/currency-conversion-feign/**`. Essas rotas são direcionadas para os serviços de destino com balanceamento de carga.
   - A rota `/currency-exchange/**` é direcionada para o serviço `currency-exchange`.
   - As rotas `/currency-conversion/**` e `/currency-conversion-feign/**` são direcionadas para o serviço `currency-conversion`.

7. Por fim, o método `build()` é chamado para criar e retornar o objeto `RouteLocator` com as rotas configuradas.

Resumindo, o código configura um roteador de gateway que define várias rotas com base nos caminhos da URL. Ele adiciona filtros personalizados a algumas rotas e direciona as solicitações para serviços de destino específicos. Isso permite que o gateway atue como um ponto de entrada único para vários serviços e fornece recursos de roteamento e filtragem.

A notação "lb://" é usada no contexto do Spring Cloud para indicar o uso do balanceamento de carga
(load balancing). Quando usado em uma rota do Spring Cloud Gateway, o "lb://" é um prefixo que indica
que a solicitação deve ser encaminhada para um serviço registrado no sistema de descoberta de serviço,
como o Eureka ou o Consul, usando o balanceamento de carga.

Ao usar "lb://", o Spring Cloud Gateway irá consultar o registro de serviços para o nome especificado após
o prefixo "lb://". Em seguida, ele usará um algoritmo de balanceamento de carga para encaminhar
a solicitação para uma instância do serviço disponível.
*/

package com.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
                .build();
    }
}
