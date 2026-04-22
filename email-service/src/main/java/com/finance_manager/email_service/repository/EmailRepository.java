package com.finance_manager.email_service.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.finance_manager.email_service.entity.EmailEntity;
@Repository
public interface EmailRepository extends JpaRepository <EmailEntity, UUID>
{
	
}