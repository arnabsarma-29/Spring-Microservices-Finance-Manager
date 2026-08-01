package com.finance_manager.email_service.dao;
import com.finance_manager.email_service.entity.EmailEntity;
public interface EmailDAO
{
	void saveEmailInfo (EmailEntity emailEntity);
}