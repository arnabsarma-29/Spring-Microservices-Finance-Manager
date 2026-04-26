package com.finance_manager.email_service.mapper;
import com.finance_manager.email_service.entity.EmailEntity;
import com.finance_manager.model.EmailModel;
public interface EmailMapper
{
	EmailEntity toEmailEntity (EmailModel emailModel, String defaultSender);
}