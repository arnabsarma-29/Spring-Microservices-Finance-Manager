package com.finance_manager.budget_service.mapper;
import org.springframework.http.ResponseEntity;
import com.finance_manager.budget_service.dto.BudgetDTO;
import com.finance_manager.budget_service.response.ResponseStructure;
public interface RequestMapper
{
	ResponseEntity <ResponseStructure <Object>> buildGenerateResponse (String message);
	ResponseEntity <ResponseStructure <Object>> buildGenerateResponse (String message, BudgetDTO budgetDTO);
}