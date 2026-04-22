package com.finance_manager.auth_service.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;
@Configuration
@ConfigurationProperties (prefix = "application.security.jwt")
@Getter
@Setter
public class JwtConfig
{
	private String secretKey;
	private long expiration;
}