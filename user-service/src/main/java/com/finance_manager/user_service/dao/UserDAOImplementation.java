package com.finance_manager.user_service.dao;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.finance_manager.user_service.entity.User;
import com.finance_manager.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
@Repository
@AllArgsConstructor
public class UserDAOImplementation implements UserDAO
{
	private final UserRepository userRepository;
	@Override
	public Optional <User> findById (UUID id)
	{
		return userRepository.findById (id);
	}
	@Override
	public Boolean existsById (UUID id)
	{
		return userRepository.existsById (id);
	}
	@Override
	public void saveUser (User user)
	{
		userRepository.save (user);
	}
	@Override
	public void deleteUser (User user)
	{
		userRepository.delete (user);
	}
}