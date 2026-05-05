package com.finance_manager.frontend_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication (scanBasePackages = "com.finance_manager")
@EnableFeignClients
public class FrontendServiceApplication
{
	public static void main (String [] args)
	{
		SpringApplication.run (FrontendServiceApplication.class, args);
	}
}