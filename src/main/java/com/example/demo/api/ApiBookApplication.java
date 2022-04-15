package com.example.demo.api;

import com.example.demo.api.controllers.BookController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ApiBookApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiBookApplication.class, args);

	}

}
