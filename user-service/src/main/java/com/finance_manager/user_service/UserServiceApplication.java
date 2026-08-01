package com.finance_manager.user_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.finance_manager.config.JwtConfig;
@SpringBootApplication (scanBasePackages = "com.finance_manager")
@EnableFeignClients
@ConfigurationPropertiesScan
@EnableConfigurationProperties (JwtConfig.class)
public class UserServiceApplication
{
	public static void main (String [] args)
	{
		SpringApplication.run (UserServiceApplication.class, args);
	}
}