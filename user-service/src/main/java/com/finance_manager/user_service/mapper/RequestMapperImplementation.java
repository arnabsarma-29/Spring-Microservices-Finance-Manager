package com.finance_manager.user_service.mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.finance_manager.user_service.dto.UserDTO;
import com.finance_manager.user_service.response.ResponseStructure;
import com.finance_manager.user_service.util.ResponseHandler;
public class RequestMapperImplementation implements RequestMapper
{
	@Override
	public ResponseEntity <ResponseStructure <Object>> buildGenerateResponse (String message)
	{
		return ResponseHandler.generateResponse (true, message, HttpStatus.OK, null);
	}
	@Override
	public ResponseEntity <ResponseStructure <Object>> buildGenerateResponse (String message, UserDTO userDTO)
	{
		return ResponseHandler.generateResponse (true, message, HttpStatus.OK, userDTO);
	}
}