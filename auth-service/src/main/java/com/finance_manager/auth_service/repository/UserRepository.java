package com.finance_manager.auth_service.repository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.finance_manager.auth_service.entity.User;
@Repository
public interface UserRepository extends JpaRepository <User, UUID>
{
	boolean existsByEmail (String email);
	Optional <User> findByEmail (String email);
}