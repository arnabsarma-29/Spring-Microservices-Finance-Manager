package com.finance_manager.budget_service.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.finance_manager.budget_service.response.ResponseStructure;
import com.finance_manager.budget_service.util.ResponseHandler;
@RestControllerAdvice
public class GlobalExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger (GlobalExceptionHandler.class);
	@ExceptionHandler (BudgetSaveException.class)
	public ResponseEntity <ResponseStructure <String>> handleBudgetSaveException (BudgetSaveException e)
	{
		logger.error ("Unexpected error in budget saving service!", e);
		return ResponseHandler.generateResponse (false, e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	@ExceptionHandler (BudgetNotFoundException.class)
	public ResponseEntity <ResponseStructure <String>> handleBudgetNotFoundException (BudgetNotFoundException e)
	{
		logger.error ("Unexpected error in budget fetching service!", e);
		return ResponseHandler.generateResponse (false, e.getMessage (), HttpStatus.NOT_FOUND, null);
	}
	@ExceptionHandler (Exception.class)
	public ResponseEntity <ResponseStructure <String>> handleGeneralException (Exception e)
	{
		logger.error ("Unexpected error in budget service!", e);
		return ResponseHandler.generateResponse (false, "Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
}