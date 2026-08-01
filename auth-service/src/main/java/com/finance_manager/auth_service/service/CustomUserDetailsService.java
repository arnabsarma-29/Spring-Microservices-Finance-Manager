package com.finance_manager.auth_service.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.finance_manager.auth_service.dao.UserDAO;
import com.finance_manager.auth_service.mapper.AuthMapper;
import com.finance_manager.exception.CustomException;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
	private final UserDAO userDAO;
	private final AuthMapper authMapper;
	@Override
	public UserDetails loadUserByUsername (String email)
	{
		return userDAO.findByEmail (email).map (authMapper :: toUserDetails).orElseThrow (() -> new CustomException ("User not found"));
	}
}