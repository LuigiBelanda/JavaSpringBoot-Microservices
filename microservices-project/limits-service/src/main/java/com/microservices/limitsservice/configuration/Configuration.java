/*
O código fornecido é uma classe de configuração em um serviço de limite (limits-service) em um
sistema de microsserviços. Essa classe é anotada com `@ConfigurationProperties("limits-service")`,
o que significa que ela é usada para mapear propriedades de configuração específicas do serviço de
limite definidas em um arquivo de propriedades ou arquivo YAML.

Quando você usa essa anotação em uma classe, o Spring Boot automaticamente associa
as propriedades com o prefixo especificado ("limits-service" neste caso) às propriedades da classe.
Isso significa que as propriedades com o prefixo "limits-service" definidas no arquivo de configuração
serão mapeadas para os campos ou métodos de acesso e configuração da classe.

No exemplo que você forneceu, a classe Configuration possui duas propriedades: minimum e maximum.
Ao usar a anotação @ConfigurationProperties("limits-service"), o Spring Boot buscará no arquivo de
configuração as propriedades com os nomes limits-service.minimum e limits-service.maximum e atribuirá
seus valores aos campos minimum e maximum da classe Configuration, respectivamente.

A classe `Configuration` possui duas propriedades: `minimum` e `maximum`, representando os valores
mínimo e máximo dos limites do serviço. Essas propriedades têm seus respectivos métodos de acesso,
`getMinimum()` e `getMaximum()`, para recuperar seus valores, e métodos de configuração, `setMinimum()`
e `setMaximum()`, para definir seus valores.

Quando o aplicativo é iniciado, o Spring Boot lê as propriedades de configuração definidas no
arquivo de propriedades ou arquivo YAML correspondente. As propriedades com o prefixo "limits-service"
são mapeadas para os campos da classe `Configuration`. Por exemplo, se houver uma propriedade chamada
`limits-service.minimum` definida no arquivo de configuração, seu valor será atribuído à propriedade
`minimum` da classe `Configuration`. O mesmo se aplica à propriedade `maximum`.

Ao usar essa classe de configuração, é possível definir e modificar facilmente os limites do serviço de
limite sem a necessidade de alterar o código-fonte do serviço em si. Isso permite que os limites sejam
configurados externamente, o que é útil para ajustar o comportamento do serviço de acordo com as necessidades
específicas de cada ambiente ou implantação.
*/

package com.microservices.limitsservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("limits-service")
public class Configuration {
    private int minimum;
    private int maximum;

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
