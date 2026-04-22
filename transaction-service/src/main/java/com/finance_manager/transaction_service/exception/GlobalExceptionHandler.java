package com.finance_manager.transaction_service.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.finance_manager.transaction_service.response.ResponseStructure;
import com.finance_manager.transaction_service.util.ResponseHandler;
@RestControllerAdvice
public class GlobalExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger (GlobalExceptionHandler.class);
	@ExceptionHandler (TransactionDBException.class)
	public ResponseEntity <ResponseStructure <String>> handleEmailSendingException (TransactionDBException e)
	{
		logger.error ("Transaction Database Error!", e);
		return buildErrorResponse (e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler (Exception.class)
	public ResponseEntity <ResponseStructure <String>> handleGeneralException (Exception e)
	{
		logger.error ("Unexpected error in transaction service!", e);
		return buildErrorResponse (e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	private ResponseEntity <ResponseStructure <String>> buildErrorResponse (String message, HttpStatus status)
	{
		return ResponseHandler.generateResponse (false, message, status, null);
	}
}