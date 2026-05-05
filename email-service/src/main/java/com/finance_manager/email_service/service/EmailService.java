package com.finance_manager.email_service.service;
import com.finance_manager.model.EmailModel;
public interface EmailService
{
	void registerEmail (EmailModel emailModel);
	void loginEmail (EmailModel emailModel);
	void passwordChange (EmailModel emailModel);
	void sendMonthlySummaryEmail (EmailModel emailModel);
}