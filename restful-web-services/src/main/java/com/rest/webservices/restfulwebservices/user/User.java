package com.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/*
A anotação @Entity(name = "user_details") é usada para indicar que a classe
User é uma entidade que deve ser persistida em um banco de dados. Vamos analisar cada detalhe:

@Entity: Essa anotação marca a classe User como uma entidade, o que significa
que ela representa uma tabela no banco de dados. A classe deve estar mapeada
corretamente para que o framework de persistência (como o Hibernate) possa gerenciar
a persistência dos objetos.

name = "user_details": Este parâmetro opcional permite especificar o nome da tabela no
banco de dados associada a essa entidade. No exemplo, a tabela correspondente será nomeada
como "user_details".

A partir dessa anotação, o framework de persistência pode criar e atualizar a tabela no
banco de dados de acordo com a estrutura definida na classe User. Além disso, ele também
permite que consultas sejam realizadas na tabela usando operações CRUD (criar, ler, atualizar
e deletar) de forma simplificada.
*/

@Entity(name = "user_details")
public class User {
    /*
    Outros elementos presentes na classe User incluem:

    @Id e @GeneratedValue: Essas anotações são usadas em conjunto para indicar que o campo id
    é a chave primária da entidade e que seu valor deve ser gerado automaticamente pelo
    sistema (geralmente usando uma estratégia de autoincremento no banco de dados).

    @Size(min = 2, message = "Name should have at least 2 characters"): Essa anotação valida
    o tamanho mínimo da string no campo name. Se o valor do campo não atender ao requisito
    mínimo, uma mensagem de erro personalizada será exibida.

    @Past(message = "Birth Date should be in the past"): Essa anotação valida se a data
    no campo birthDate é anterior à data atual. Se a data fornecida estiver no futuro,
    uma mensagem de erro personalizada será exibida.

    Essas anotações de validação são parte do mecanismo de validação fornecido pelo Spring
    Framework. Elas são úteis para garantir que os dados inseridos ou atualizados na entidade
    atendam a determinadas regras de validação antes de serem persistidos no banco de dados.
    */
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    @JsonProperty("user_name")
    private String name;

    @Past(message = "Birth Data should be in the past")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
