package com.finance_manager.auth_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan (basePackages = "com.finance_manager")
public class AuthServiceApplication
{
	public static void main (String [] args)
	{
		SpringApplication.run (AuthServiceApplication.class, args);
	}
}