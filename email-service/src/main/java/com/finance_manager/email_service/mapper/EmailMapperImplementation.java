package com.finance_manager.email_service.mapper;
import com.finance_manager.email_service.entity.EmailEntity;
import com.finance_manager.email_service.model.EmailModel;
public class EmailMapperImplementation implements EmailMapper
{
	public EmailEntity toEmailEntity (EmailModel emailModel, String defaultSender)
	{
		return new EmailEntity (defaultSender, emailModel.getReceiver (), emailModel.getSubject (), emailModel.getBody ());
	}
}