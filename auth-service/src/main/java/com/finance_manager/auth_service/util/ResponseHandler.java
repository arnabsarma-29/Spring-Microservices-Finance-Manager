package com.finance_manager.auth_service.util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.finance_manager.auth_service.response.ResponseStructure;
public class ResponseHandler
{
	public static <T> ResponseEntity <ResponseStructure <T>> generateResponse (boolean success, String message, HttpStatus status, T data)
	{
		ResponseStructure <T> structure = new ResponseStructure <> (success, status.value (), message, data);
		return new ResponseEntity <> (structure, status);
	}
}