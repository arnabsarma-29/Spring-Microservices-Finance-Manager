package com.finance_manager.user_service.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.finance_manager.user_service.entity.User;
public interface UserRepository extends JpaRepository <User, UUID>
{
	
}