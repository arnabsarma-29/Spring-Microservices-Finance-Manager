package com.finance_manager.frontend_service.config;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.finance_manager.security.CustomPrincipal;
import com.finance_manager.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class SessionAuthFilter extends OncePerRequestFilter
{
	private final JwtService jwtService;
	@Override
	protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
	{
		HttpSession session = request.getSession (false);
		if (session != null && session.getAttribute ("token") != null)
		{
			String token = (String) session.getAttribute ("token");
			if (jwtService.isTokenValid (token))
			{
				UUID userId = jwtService.extractUserId (token);
				String email = jwtService.extractEmail (token);
				CustomPrincipal principal = CustomPrincipal.builder ().id (userId).email (email).build ();
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken (principal, null, Collections.emptyList ());
				SecurityContextHolder.getContext ().setAuthentication (auth);
			}
		}
		filterChain.doFilter (request, response);
	}
}