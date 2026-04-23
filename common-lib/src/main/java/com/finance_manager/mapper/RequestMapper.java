package com.finance_manager.mapper;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.finance_manager.response.ResponseStructure;
public interface RequestMapper
{
	<T> ResponseEntity <ResponseStructure <T>> buildGenerateResponse (String message, HttpStatus status);
	<T> ResponseEntity <ResponseStructure <T>> buildGenerateResponse (String message, HttpStatus status, T data);
	<T> ResponseEntity <ResponseStructure <List <T>>> buildGenerateResponse (String message, HttpStatus status, List <T> data);
}