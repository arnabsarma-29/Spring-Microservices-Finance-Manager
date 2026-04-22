package com.finance_manager.user_service.mapper;
import org.springframework.http.ResponseEntity;
import com.finance_manager.user_service.dto.UserDTO;
import com.finance_manager.user_service.response.ResponseStructure;
public interface RequestMapper
{
	ResponseEntity <ResponseStructure <Object>> buildGenerateResponse (String message);
	ResponseEntity <ResponseStructure <Object>> buildGenerateResponse (String message, UserDTO userDTO);
}