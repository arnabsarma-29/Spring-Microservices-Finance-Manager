package com.finance_manager.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.finance_manager.response.ResponseStructure;
import com.finance_manager.util.ResponseHandler;
@RestControllerAdvice ("backendExceptionHandler")
public class GlobalExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger (GlobalExceptionHandler.class);
	@ExceptionHandler (CustomException.class)
	public ResponseEntity <ResponseStructure <Void>> handleCustomException (CustomException e)
	{
		logger.error ("Unhandled exception: {}", e.getMessage (), e);
		return ResponseHandler.generateResponse (false, e.getMessage (), HttpStatus.BAD_REQUEST, null);

	}
	@ExceptionHandler (Exception.class)
	public ResponseEntity <ResponseStructure <Void>> handleGeneralException (Exception e)
	{
		logger.error ("Unexpected exception occurred!", e);
		return ResponseHandler.generateResponse (false, "Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
}