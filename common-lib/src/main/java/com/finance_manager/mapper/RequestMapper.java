package com.finance_manager.mapper;
import org.springframework.http.ResponseEntity;
import com.finance_manager.response.ResponseStructure;
public interface RequestMapper
{
	<T> ResponseEntity <ResponseStructure <T>> buildGenerateResponse (String message);
	<T> ResponseEntity <ResponseStructure <T>> buildGenerateResponse (String message, T data);
}