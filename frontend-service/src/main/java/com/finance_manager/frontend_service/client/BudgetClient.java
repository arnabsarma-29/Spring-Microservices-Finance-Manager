package com.finance_manager.frontend_service.client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.response.ResponseStructure;
public interface BudgetClient
{
	@PostMapping ("/save")
	ResponseStructure <Void> saveBudget (@RequestBody BudgetModel budgetModel);
	@GetMapping ("/getBudget")
	ResponseStructure <BudgetDTO> getBudget ();
}