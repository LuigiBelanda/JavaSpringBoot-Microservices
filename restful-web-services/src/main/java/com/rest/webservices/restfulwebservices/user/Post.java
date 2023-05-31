package com.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 10)
    private String description;

    /*
    A anotação @ManyToOne é utilizada na JPA (Java Persistence API) para mapear um relacionamento de
    muitos para um (many-to-one) entre entidades em um banco de dados relacional. Nesse caso,
    o atributo user está sendo mapeado como uma relação muitos para um com a entidade User.

    Além disso, a anotação fetch = FetchType.LAZY especifica que o carregamento dos objetos
    relacionados (neste caso, a entidade User) será feito de forma preguiçosa (lazy), ou
    seja, somente quando o objeto for acessado explicitamente. Isso pode ajudar a melhorar
    o desempenho em determinadas situações, evitando carregamentos desnecessários de objetos
    relacionados.

    Uma relação many-to-one (muitos para um) é um tipo de relacionamento entre duas entidades em
    um modelo de dados, onde várias instâncias de uma entidade estão associadas a uma única
    instância de outra entidade.

    Nesse tipo de relacionamento, várias instâncias de uma entidade A podem estar
    relacionadas a uma única instância de uma entidade B. Em outras palavras, várias entidades
    A se referem à mesma entidade B.
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
