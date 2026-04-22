package com.finance_manager.auth_service.dao;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.finance_manager.auth_service.entity.User;
import com.finance_manager.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class UserDAOImplementation implements UserDAO
{
	private final UserRepository userRepository;
	@Override
	public User saveUser (User user)
	{
		return userRepository.save (user);
	}
	@Override
	public boolean existsByEmail (String email)
	{
		return userRepository.existsByEmail (email);
	}
	@Override
	public Optional <User> findByEmail (String email)
	{
		return userRepository.findByEmail (email);
	}
	@Override
	public void deleteUser (User user)
	{
		userRepository.delete (user);
	}
}