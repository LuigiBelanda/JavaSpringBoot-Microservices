/*
A classe `VersioningPersonController` é um controlador Spring Boot que implementa diferentes
estratégias de versionamento de API. Vamos analisar o código e explicar cada estratégia:

1. Versionamento baseado em URL:
   - O método `getFirstVersionOfPerson()` é mapeado para a URL "/v1/person" e retorna uma instância de `PersonV1`.
   - O método `getSecondVersionOfPerson()` é mapeado para a URL "/v2/person" e retorna uma instância de `PersonV2`.
   - Essa abordagem permite que os clientes acessem versões específicas da API fornecendo a versão desejada na URL.

2. Versionamento baseado em parâmetro de solicitação:
   - O método `getFirstVersionOfPersonRequestParameter()` é mapeado para a URL "/person" com o parâmetro "version=1" e retorna uma instância de `PersonV1`.
   - O método `getSecondVersionOfPersonRequestParameter()` é mapeado para a URL "/person" com o parâmetro "version=2" e retorna uma instância de `PersonV2`.
   - Essa abordagem permite que os clientes acessem versões específicas da API fornecendo o parâmetro "version" na solicitação.

3. Versionamento baseado em cabeçalho de solicitação:
   - O método `getFirstVersionOfPersonRequestHeader()` é mapeado para a URL "/person/header" com o cabeçalho "X-API-VERSION=1" e retorna uma instância de `PersonV1`.
   - O método `getSecondVersionOfPersonRequestHeader()` é mapeado para a URL "/person/header" com o cabeçalho "X-API-VERSION=2" e retorna uma instância de `PersonV2`.
   - Essa abordagem permite que os clientes acessem versões específicas da API fornecendo o cabeçalho "X-API-VERSION" na solicitação.

4. Versionamento baseado em cabeçalho "Accept":
   - O método `getFirstVersionOfPersonAcceptHeader()` é mapeado para a URL "/person/accept" com o cabeçalho "Accept" definido como "application/vnd.company.app-v1+json" e retorna uma instância de `PersonV1`.
   - O método `getSecondVersionOfPersonAcceptHeader()` é mapeado para a URL "/person/accept" com o cabeçalho "Accept" definido como "application/vnd.company.app-v2+json" e retorna uma instância de `PersonV2`.
   - Essa abordagem permite que os clientes acessem versões específicas da API fornecendo o cabeçalho "Accept" com um valor personalizado que representa a versão desejada.

Essas estratégias de versionamento permitem que os desenvolvedores controlem a evolução da API e forneçam
diferentes versões para atender às necessidades dos clientes sem interromper as versões anteriores.
Cada abordagem tem suas vantagens e pode ser escolhida com base nos requisitos específicos do projeto
e nas preferências da equipe de desenvolvimento.
*/

package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV2 getSecondVersionOfPersonRequestHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
