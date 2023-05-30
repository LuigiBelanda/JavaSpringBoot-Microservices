/*
Este é um exemplo de um controlador em um aplicativo de serviço da web RESTful que demonstra
o uso de filtragem de propriedades com a biblioteca Jackson em um endpoint que retorna dados JSON.

No controlador `FilteringController`, existem dois métodos de endpoint mapeados com as
anotações `@GetMapping`. Vamos analisar cada um deles:

1. Método `filtering()`:
   - Este método responde ao endpoint "/filtering".
   - Cria uma instância da classe `SomeBean` com os valores "value1", "value2" e "value3".
   - Em seguida, cria um objeto `MappingJacksonValue` e o inicializa com a instância `someBean`.
   - Define um filtro `SimpleBeanPropertyFilter` para filtrar todas as propriedades, exceto "filed1" e "field3".
   - Cria um provedor de filtros `SimpleFilterProvider` e adiciona o filtro com o nome "SomeBeanFilter".
   - Define os filtros no objeto `MappingJacksonValue`.
   - Retorna o objeto `MappingJacksonValue`, que será convertido em JSON e enviado como resposta.

2. Método `filteringList()`:
   - Este método responde ao endpoint "/filtering-list".
   - Cria uma lista de instâncias da classe `SomeBean` com os valores "value4", "value5" e "value6".
   - Em seguida, cria um objeto `MappingJacksonValue` e o inicializa com a lista.
   - Define um filtro `SimpleBeanPropertyFilter` para filtrar todas as propriedades, exceto "filed2" e "field3".
   - Cria um provedor de filtros `SimpleFilterProvider` e adiciona o filtro com o nome "SomeBeanFilter".
   - Define os filtros no objeto `MappingJacksonValue`.
   - Retorna o objeto `MappingJacksonValue`, que será convertido em JSON e enviado como resposta.

No geral, esse controlador usa o `MappingJacksonValue` em combinação com
`SimpleBeanPropertyFilter` e `SimpleFilterProvider` para filtrar quais propriedades de um
objeto ou lista são incluídas na resposta JSON. Os filtros são adicionados usando um nome de
filtro personalizado ("SomeBeanFilter" neste caso) para que possam ser aplicados aos objetos
`MappingJacksonValue`.
*/

package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        MappingJacksonValue mappingJacksonValue =new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("filed1", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> list = Arrays.asList(new SomeBean("value4", "value5", "value6"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("filed2", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
