package com.finance_manager.auth_service.service;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.finance_manager.auth_service.dao.UserDAO;
import com.finance_manager.auth_service.dto.UserDTO;
import com.finance_manager.auth_service.mapper.AuthMapper;
import com.finance_manager.exception.CustomException;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CurrentUserProvider
{
	private final UserDAO userDAO;
	private final AuthMapper authMapper;
	public UserDTO getCurrentUser ()
	{
		Authentication auth = SecurityContextHolder.getContext ().getAuthentication ();
		if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken)
		{
			throw new CustomException ("No authenticated user session found");
		}
		return userDAO.findByEmail (auth.getName ()).map (authMapper :: toUserDTO).orElseThrow (() -> new CustomException ("User not found in system."));
	}
}