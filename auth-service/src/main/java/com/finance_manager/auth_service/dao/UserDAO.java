package com.finance_manager.auth_service.dao;
import java.util.Optional;
import com.finance_manager.auth_service.entity.User;
public interface UserDAO
{
	User saveUser (User user);
	boolean existsByEmail (String email);
	Optional <User> findByEmail (String email);
	void deleteUser (User user);
}