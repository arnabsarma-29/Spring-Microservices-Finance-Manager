package com.finance_manager.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;
@ConfigurationProperties (prefix = "application.security.jwt")
@Getter
@Setter
public class JwtConfig
{
	private String secretKey;
	private long expiration;
}