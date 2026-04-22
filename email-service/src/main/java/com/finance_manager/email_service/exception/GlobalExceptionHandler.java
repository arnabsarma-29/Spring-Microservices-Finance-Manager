package com.finance_manager.email_service.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.finance_manager.email_service.response.ResponseStructure;
import com.finance_manager.email_service.util.ResponseHandler;
@RestControllerAdvice
public class GlobalExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger (GlobalExceptionHandler.class);
	@ExceptionHandler (EmailSendingException.class)
	public ResponseEntity <ResponseStructure <String>> handleEmailSendingException (EmailSendingException e)
	{
		logger.error ("Email sending failed!", e);
		return buildErrorResponse (e.getMessage (), HttpStatus.BAD_GATEWAY);
	}
	@ExceptionHandler (EmailSaveException.class)
	public ResponseEntity <ResponseStructure <String>> handleEmailSaveException (EmailSaveException e)
	{
		logger.error ("Email saving failed!", e);
		return buildErrorResponse (e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler (Exception.class)
	public ResponseEntity <ResponseStructure <String>> handleGeneralException (Exception e)
	{
		logger.error ("Unexpected error in email service!", e);
		return buildErrorResponse (e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	private ResponseEntity <ResponseStructure <String>> buildErrorResponse (String message, HttpStatus status)
	{
		return ResponseHandler.generateResponse (false, message, status, null);
	}
}