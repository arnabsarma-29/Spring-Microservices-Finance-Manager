package com.finance_manager.auth_service.service;
import java.util.Collections;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.finance_manager.auth_service.dao.UserDAO;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
	private final UserDAO userDAO;
	@Override
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException
	{
		return userDAO.findByEmail (email).map (user -> new User (email, user.getPassword (), Collections.emptyList ())).orElseThrow (() -> new UsernameNotFoundException ("User not found"));
	}
}