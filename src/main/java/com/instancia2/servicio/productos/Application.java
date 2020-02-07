package com.instancia2.servicio.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.instancia2.servicio.commons.model"})
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}
