package com.finance_manager.auth_service.service;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.finance_manager.auth_service.dao.UserDAO;
import com.finance_manager.auth_service.dto.UserDTO;
import com.finance_manager.auth_service.entity.User;
import com.finance_manager.auth_service.exception.NoSessionFound;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CurrentUserProvider
{
	private final UserDAO userDAO;
	public UserDTO getCurrentUser ()
	{
		Authentication auth = SecurityContextHolder.getContext ().getAuthentication ();
		if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken)
		{
			throw new NoSessionFound ("No authenticated user session found");
		}
		return mapToDTO (userDAO.findByEmail (auth.getName ()).orElseThrow (() -> new NoSessionFound ("User not found in system.")));
	}
	private UserDTO mapToDTO (User user)
	{
		return UserDTO.builder ().email (user.getEmail ()).build ();
	}
}