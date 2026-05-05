package com.finance_manager.transaction_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication (scanBasePackages = "com.finance_manager")
@EnableScheduling
@EnableFeignClients
public class TransactionServiceApplication
{
	public static void main (String [] args)
	{
		SpringApplication.run (TransactionServiceApplication.class, args);
	}
}