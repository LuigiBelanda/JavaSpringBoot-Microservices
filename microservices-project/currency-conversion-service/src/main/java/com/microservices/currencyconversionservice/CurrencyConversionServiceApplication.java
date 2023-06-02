package com.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
A anotação @EnableFeignClients é usada para habilitar o suporte ao cliente Feign na aplicação.
O Feign é uma biblioteca do Spring Cloud usada para facilitar a comunicação com serviços RESTful.
Essa anotação instrui o Spring a procurar interfaces anotadas com @FeignClient e criar
implementações dessas interfaces automaticamente.
*/

@SpringBootApplication
@EnableFeignClients
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

}
