package com.finance_manager.user_service.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.finance_manager.user_service.response.ResponseStructure;
import com.finance_manager.user_service.util.ResponseHandler;
@RestControllerAdvice
public class GlobalExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger (GlobalExceptionHandler.class);
	@ExceptionHandler (UserAlreadyExists.class)
	public ResponseEntity <ResponseStructure <String>> handleUserAlreadyExists (UserAlreadyExists e)
	{
		logger.warn ("User already exists during registration: {} (Code: {})", e.getMessage (), e.getErrorCode ().getCode ());
		return ResponseHandler.generateResponse (false, e.getMessage (), HttpStatus.CONFLICT, null);
	}
	@ExceptionHandler (MethodArgumentNotValidException.class)
	public ResponseEntity <ResponseStructure <String>> handleValidationException (MethodArgumentNotValidException e)
	{
		String message = e.getBindingResult ().getFieldError ().getDefaultMessage ();
		return ResponseHandler.generateResponse (false, message, HttpStatus.BAD_REQUEST, null);
	}
	@ExceptionHandler (DataIntegrityViolationException.class)
	public ResponseEntity <ResponseStructure <String>> handleDataIntegrity (DataIntegrityViolationException e)
	{
		return ResponseHandler.generateResponse (false, "Data conflict: This email might already be in use.", HttpStatus.CONFLICT, null);
	}
	@ExceptionHandler (Exception.class)
	public ResponseEntity <ResponseStructure <String>> handleGeneralException (Exception e)
	{
		logger.error ("Unexpected error in auth service", e);
		return ResponseHandler.generateResponse (false, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
}