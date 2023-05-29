/*
A classe `HelloWorldBean` é uma classe simples usada no exemplo anterior para
representar um objeto que contém uma mensagem. Vamos analisar o código e explicar
suas funcionalidades:

1. A classe `HelloWorldBean` possui um campo privado `message`, que armazena a
mensagem desejada.

2. O construtor `HelloWorldBean(String message)` é utilizado para criar
uma instância da classe `HelloWorldBean` e definir o valor da mensagem.

3. O método getter `getMessage()` retorna o valor da mensagem armazenada
no objeto `HelloWorldBean`.

4. O método setter `setMessage(String message)` é usado para definir o
valor da mensagem no objeto `HelloWorldBean`.

5. O método `toString()` é sobrescrito para fornecer uma representação em
string do objeto `HelloWorldBean`. Nesse caso, ele retorna uma string que
contém o nome da classe e o valor da mensagem.

Essa classe é utilizada no controlador `HelloWorldController`
para retornar um objeto `HelloWorldBean` serializado em formato JSON como
resposta a uma solicitação HTTP GET. O objeto `HelloWorldBean` é criado
com uma mensagem específica e é retornado no corpo da resposta HTTP como uma
representação do objeto no formato JSON.
*/

package com.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
