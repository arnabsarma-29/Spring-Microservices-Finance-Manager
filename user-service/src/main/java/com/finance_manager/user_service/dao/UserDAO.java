package com.finance_manager.user_service.dao;
import java.util.Optional;
import java.util.UUID;
import com.finance_manager.user_service.entity.User;
public interface UserDAO
{
	Optional <User> findById (UUID id);
	Boolean existsById (UUID id);
	void saveUser (User user);
	void deleteUser (User user);
}