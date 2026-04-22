package com.finance_manager.budget_service.mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.finance_manager.budget_service.dto.BudgetDTO;
import com.finance_manager.budget_service.response.ResponseStructure;
import com.finance_manager.budget_service.util.ResponseHandler;
@Component
public class RequestMapperImplementation implements RequestMapper
{
	@Override
	public ResponseEntity <ResponseStructure <Object>> buildGenerateResponse (String message)
	{
		return ResponseHandler.generateResponse (true, message, HttpStatus.OK, null);
	}
	@Override
	public ResponseEntity <ResponseStructure <Object>> buildGenerateResponse (String message, BudgetDTO budgetDTO)
	{
		return ResponseHandler.generateResponse (true, message, HttpStatus.OK, budgetDTO);
	}
}