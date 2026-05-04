package com.finance_manager.frontend_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@EnableFeignClients
@ComponentScan (basePackages = "com.finance_manager")
public class FrontendServiceApplication
{
	public static void main (String [] args)
	{
		SpringApplication.run (FrontendServiceApplication.class, args);
	}
}