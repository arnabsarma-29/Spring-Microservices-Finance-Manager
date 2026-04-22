package com.finance_manager.email_service.service;
import com.finance_manager.email_service.model.EmailModel;
import jakarta.validation.Valid;
public interface EmailService
{
	void sendSimpleEmail (@Valid EmailModel emailModel);
	void registerEmail (EmailModel emailModel);
	void loginEmail (EmailModel emailModel);
	void passwordChange (EmailModel emailModel);
	void sendMonthlySummaryEmail (EmailModel emailModel);
}