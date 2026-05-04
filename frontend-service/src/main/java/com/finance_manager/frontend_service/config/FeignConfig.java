package com.finance_manager.frontend_service.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import feign.RequestInterceptor;
import jakarta.servlet.http.HttpSession;
@Configuration
public class FeignConfig
{
	@Bean
	public RequestInterceptor requestInterceptor ()
	{
		return requestTemplate ->
		{
			ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes ();
			if (attrs != null)
			{
				HttpSession session = attrs.getRequest ().getSession (false);
				if (session != null)
				{
					String token = (String) session.getAttribute ("token");
					if (token != null)
					{
						requestTemplate.header ("Authorization", "Bearer " + token);
					}
				}
			}
		};
	}
}