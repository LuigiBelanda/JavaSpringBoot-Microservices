/*
A classe `User` é uma classe simples que representa um usuário em um sistema.
Vamos analisar o código e explicar suas funcionalidades:

1. A classe `User` possui três campos privados: `id` (do tipo `Integer`),
`name` (do tipo `String`) e `birthDate` (do tipo `LocalDate`). Esses campos
representam o ID, o nome e a data de nascimento do usuário, respectivamente.

2. O construtor `User(Integer id, String name, LocalDate birthDate)` é utilizado
para criar uma instância da classe `User` e definir os valores do ID,
nome e data de nascimento.

3. Os métodos getters e setters são usados para acessar e modificar os
valores dos campos `id`, `name` e `birthDate`.

4. O método `toString()` é sobrescrito para fornecer uma representação em
string do objeto `User`. Nesse caso, ele retorna uma string que contém o
nome da classe e os valores dos campos `id`, `name` e `birthDate`.

Resumindo, a classe `User` é um modelo de dados que representa um usuário
com suas informações básicas, como ID, nome e data de nascimento. Ela fornece
métodos para acessar e modificar essas informações, além de ter uma implementação
personalizada do método `toString()` para facilitar a representação em string do
objeto. Essa classe pode ser usada em conjunto com outras classes e componentes
para manipular dados de usuário em um sistema.
*/

package com.rest.webservices.restfulwebservices.user;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class User {
    private Integer id;
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @Past(message = "Birth Data should be in the past")
    private LocalDate birthDate;

    /*
    As anotações `@Size` e `@Past` são anotações de validação utilizadas para impor
    restrições nos campos da classe. Vamos analisar cada uma delas:

    1. `@Size(min = 2, message = "Name should have at least 2 characters")`:
    Essa anotação é utilizada para validar o tamanho de uma string. No exemplo,
    ela é aplicada ao campo `name` e define que o tamanho mínimo dessa string
    deve ser 2 caracteres. Se o valor do campo `name` tiver menos de 2 caracteres,
    será lançada uma exceção de validação com a mensagem definida no parâmetro `message`.

    2. `@Past(message = "Birth Date should be in the past")`: Essa anotação é
    utilizada para validar uma data e assegurar que ela esteja no passado. No
    exemplo, ela é aplicada ao campo `birthDate` e define que a data deve ser
    anterior à data atual. Se o valor do campo `birthDate` for uma data futura,
    será lançada uma exceção de validação com a mensagem definida no parâmetro `message`.

    Essas anotações são parte do mecanismo de validação do Spring Framework e
    são usadas em conjunto com o `@Valid` nas classes de entidade para garantir
    que os dados atendam a determinadas regras de validação antes de serem processados.
    Ao aplicar essas anotações, o Spring realiza automaticamente a validação dos
    campos com base nas regras definidas, facilitando a implementação de lógica de
    validação nas classes da aplicação.
    */

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
