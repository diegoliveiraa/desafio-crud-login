package com.diegoliveiraa.desafio_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCrudApplication.class, args);
	}

}
