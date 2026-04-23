package com.finance_manager.mapper;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.finance_manager.response.ResponseStructure;
import com.finance_manager.util.ResponseHandler;
@Component
public class RequestMapperImplementation implements RequestMapper
{
	@Override
	public <T> ResponseEntity <ResponseStructure <T>> buildGenerateResponse (String message, HttpStatus status)
	{
		return ResponseHandler.generateResponse (true, message, HttpStatus.OK, null);
	}
	@Override
	public <T> ResponseEntity <ResponseStructure <T>> buildGenerateResponse (String message, HttpStatus status, T data)
	{
		return ResponseHandler.generateResponse (true, message, HttpStatus.OK, data);
	}
	@Override
	public <T> ResponseEntity <ResponseStructure <List <T>>> buildGenerateResponse (String message, HttpStatus status, List <T> data)
	{
		return ResponseHandler.generateResponse (true, message, HttpStatus.OK, data);
	}
}