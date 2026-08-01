package com.finance_manager.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;
@ConfigurationProperties (prefix = "application.security.jwt")
@Configuration
@Data
public class JwtConfig
{
	private String secretKey;
	private long expiration;
}