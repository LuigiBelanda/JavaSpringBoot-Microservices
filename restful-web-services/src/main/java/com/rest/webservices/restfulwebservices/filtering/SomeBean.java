/*
A annotation `@JsonIgnoreProperties("field1")` é usada para indicar que a propriedade
chamada "field1" deve ser ignorada durante a serialização e desserialização de objetos
JSON. Isso significa que quando um objeto da classe `SomeBean` é convertido em JSON,
a propriedade "field1" não será incluída no JSON resultante, e quando um JSON é convertido
em um objeto da classe `SomeBean`, o valor correspondente à propriedade "field1" no JSON será ignorado.

A annotation `@JsonIgnore` é usada para indicar que uma determinada propriedade, nesse caso
"field2", deve ser ignorada durante a serialização e desserialização de objetos JSON. Isso
significa que a propriedade "field2" não será incluída no JSON resultante nem será considerada
quando um JSON é convertido em um objeto da classe `SomeBean`.

Por padrão, durante a serialização/desserialização de objetos Java em JSON, todas as propriedades
públicas e privadas são consideradas, a menos que sejam especificamente ignoradas usando as
annotations `@JsonIgnore` ou `@JsonIgnoreProperties`. Essas annotations são úteis quando você
deseja excluir determinadas propriedades de um objeto Java da representação JSON, como quando a
propriedade contém informações sensíveis ou não é relevante para o contexto de serialização/desserialização.
*/

package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// @JsonIgnoreProperties("field1")
public class SomeBean {
    private String field1;
    @JsonIgnore
    private String field2;
    private String field3;

    public SomeBean(String filed1, String filed2, String filed3) {
        this.field1 = filed1;
        this.field2 = filed2;
        this.field3 = filed3;
    }

    public String getFiled1() {
        return field1;
    }

    public String getFiled2() {
        return field2;
    }

    public String getFiled3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "filed1='" + field1 + '\'' +
                ", filed2='" + field2 + '\'' +
                ", filed3='" + field3 + '\'' +
                '}';
    }
}
