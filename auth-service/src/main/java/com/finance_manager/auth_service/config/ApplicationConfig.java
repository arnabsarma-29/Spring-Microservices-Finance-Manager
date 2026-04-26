package com.finance_manager.auth_service.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import com.finance_manager.auth_service.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig
{
	private final CustomUserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	@Bean
	public AuthenticationProvider authenticationProvider ()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider (userDetailsService);
		authProvider.setPasswordEncoder (passwordEncoder);
		return authProvider;
	}
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager ();
	}
	@Bean
	public RestTemplate restTemplate ()
	{
		return new RestTemplate ();
	}
}