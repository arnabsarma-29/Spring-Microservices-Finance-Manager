package com.finance_manager.security;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.finance_manager.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter
{
	private final JwtService jwtService;
	@Override
	protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
	{
		String authHeader = request.getHeader ("Authorization");
		if (authHeader == null || !authHeader.startsWith ("Bearer "))
		{
			filterChain.doFilter (request, response);
			return;
		}
		String token = authHeader.substring (7);
		try
		{
			if (jwtService.isTokenValid (token))
			{
				UUID userId = jwtService.extractUserId (token);
				String email = jwtService.extractEmail (token);
				if (userId != null && SecurityContextHolder.getContext ().getAuthentication () == null)
				{
					CustomPrincipal principal = new CustomPrincipal (userId, email);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken (principal, null, Collections.emptyList ());
					authToken.setDetails (new WebAuthenticationDetailsSource ().buildDetails (request));
					SecurityContextHolder.getContext ().setAuthentication (authToken);
				}
			}
		}
		catch (Exception e)
		{
			log.error ("Could not set user authentication in security context", e);
                }
		filterChain.doFilter (request, response);
	}
}