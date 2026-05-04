package com.finance_manager.frontend_service.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.AllArgsConstructor;
@Configuration
@AllArgsConstructor
public class SecurityConfig
{
	private final SessionAuthFilter sessionAuthFilter;
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception
	{
		http.csrf (csrf -> csrf.disable ()).authorizeHttpRequests (auth -> auth.requestMatchers ("/", "/login", "/profile/register", "/profile/login", "/css/**", "/js/**").permitAll ().anyRequest ().authenticated ()).addFilterBefore (sessionAuthFilter, UsernamePasswordAuthenticationFilter.class).logout (logout -> logout.logoutUrl ("/logout").logoutSuccessUrl ("/login").invalidateHttpSession (true));
		return http.build ();
	}
}