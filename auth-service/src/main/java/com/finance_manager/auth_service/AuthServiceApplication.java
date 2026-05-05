package com.finance_manager.auth_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.finance_manager.config.JwtConfig;
@SpringBootApplication (scanBasePackages = "com.finance_manager")
@ConfigurationPropertiesScan
@EnableConfigurationProperties (JwtConfig.class)
@EnableFeignClients
public class AuthServiceApplication
{
	public static void main (String [] args)
	{
		SpringApplication.run (AuthServiceApplication.class, args);
	}
}