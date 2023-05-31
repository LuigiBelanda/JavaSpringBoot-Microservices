/*
O código fornecido é uma classe de configuração para a segurança do Spring Security em uma aplicação web.

A classe `SpringSecurityConfiguration` é anotada com `@Configuration`, indicando que é
uma classe de configuração do Spring.

Dentro dessa classe, temos um método `filterChain(HttpSecurity http)` anotado com `@Bean`,
que retorna um objeto `SecurityFilterChain`. Esse método configura as regras de segurança para a aplicação.

Vamos explicar cada uma das configurações realizadas no método:

1. `http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())` configura a autorização
para todas as requisições HTTP. Ele define que todas as requisições devem estar autenticadas,
ou seja, é necessário fornecer credenciais válidas para acessar qualquer recurso da aplicação.

2. `http.httpBasic(Customizer.withDefaults())` configura o mecanismo de autenticação básica do
Spring Security. A autenticação básica envia as credenciais do usuário (nome de usuário e senha) de
forma codificada no cabeçalho `Authorization` das requisições HTTP.

3. `http.csrf().disable()` desabilita a proteção CSRF (Cross-Site Request Forgery) do Spring Security.
O CSRF é uma medida de segurança que protege contra ataques em que um site malicioso faz solicitações
não autorizadas em nome de um usuário autenticado. Neste caso, a proteção CSRF está sendo desabilitada,
o que significa que a aplicação não irá verificar a presença de tokens CSRF em solicitações.

Por fim, o método retorna o objeto `http` após todas as configurações serem aplicadas usando o método
`http.build()`, e esse objeto é utilizado como um filtro de segurança (`SecurityFilterChain`) para a aplicação.

Essa classe é apenas um exemplo básico de configuração do Spring Security e pode ser personalizada para
atender aos requisitos específicos de segurança da aplicação.
*/

package com.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        http.httpBasic(Customizer.withDefaults());

        http.csrf().disable();

        return http.build();
    }
}
