package com.finance_manager.frontend_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.model.BudgetModel;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "budget-service", url = "http://localhost:8501/budget")
public interface BudgetClient
{
	@PostMapping ("/save")
	ResponseStructure <Void> saveBudget (@RequestBody BudgetModel budgetModel);
	@GetMapping ("/getBudget")
	ResponseStructure <BudgetDTO> getBudget ();
}