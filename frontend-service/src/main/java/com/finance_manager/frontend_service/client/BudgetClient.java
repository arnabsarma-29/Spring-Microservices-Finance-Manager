package com.finance_manager.frontend_service.client;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.model.BudgetModel;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "budget-service", url = "http://localhost:8501/budget")
public interface BudgetClient
{
	@PostMapping ("/saveBudget")
	ResponseStructure <Void> saveBudget (@RequestBody BudgetModel budgetModel);
	@PatchMapping ("/editBudget")
	ResponseStructure <Void> editBudget (@RequestBody BudgetModel budgetModel);
	@PostMapping ("/deleteBudget/{id}")
	ResponseStructure <Void> deleteBudget (@PathVariable UUID id);
	@GetMapping ("/getAllBudgets")
	ResponseStructure <List <BudgetDTO>> getAllBudgets ();
}