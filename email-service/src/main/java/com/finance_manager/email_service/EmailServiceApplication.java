package com.finance_manager.email_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.finance_manager.config.JwtConfig;
@SpringBootApplication (scanBasePackages = "com.finance_manager")
@ConfigurationPropertiesScan
@EnableConfigurationProperties (JwtConfig.class)
public class EmailServiceApplication
{
	public static void main (String [] args)
	{
		SpringApplication.run (EmailServiceApplication.class, args);
	}
}