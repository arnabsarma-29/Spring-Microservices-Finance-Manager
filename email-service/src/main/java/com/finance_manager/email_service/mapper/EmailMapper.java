package com.finance_manager.email_service.mapper;
import org.mapstruct.Mapper;
import com.finance_manager.email_service.entity.EmailEntity;
import com.finance_manager.model.EmailModel;
@Mapper (componentModel = "spring")
public interface EmailMapper
{
	EmailEntity toEmailEntity (EmailModel emailModel, String defaultSender);
}