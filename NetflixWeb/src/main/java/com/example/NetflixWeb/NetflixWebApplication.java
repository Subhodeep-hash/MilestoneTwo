package com.example.NetflixWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.example.NetflixWeb.Controller,com.example.NetflixWeb.Service,package com.example.NetflixWeb.Model, com.example.NetflixWeb.Service.Reader"})
public class NetflixWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixWebApplication.class, args);
	}

}
