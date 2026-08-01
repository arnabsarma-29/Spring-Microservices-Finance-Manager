package com.finance_manager.email_service.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.finance_manager.email_service.entity.EmailEntity;
public interface EmailRepository extends JpaRepository <EmailEntity, UUID>
{
	
}