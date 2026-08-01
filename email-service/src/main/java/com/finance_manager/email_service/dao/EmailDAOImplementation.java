package com.finance_manager.email_service.dao;
import org.springframework.stereotype.Repository;
import com.finance_manager.email_service.entity.EmailEntity;
import com.finance_manager.email_service.repository.EmailRepository;
import lombok.AllArgsConstructor;
@Repository
@AllArgsConstructor
public class EmailDAOImplementation implements EmailDAO
{
	private final EmailRepository emailRepository;
	@Override
	public void saveEmailInfo (EmailEntity emailEntity)
	{
		emailRepository.save (emailEntity);
	}
}