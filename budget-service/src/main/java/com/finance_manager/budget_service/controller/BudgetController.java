package com.finance_manager.budget_service.controller;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.budget_service.service.BudgetService;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.mapper.RequestMapper;
import com.finance_manager.model.BudgetModel;
import com.finance_manager.response.ResponseStructure;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@AllArgsConstructor
@RequestMapping ("/budget")
public class BudgetController
{
	private final BudgetService budgetService;
	private final RequestMapper requestMapper;
	@PostMapping ("/saveBudget")
	public ResponseEntity <ResponseStructure <Void>> saveBudget (@RequestBody BudgetModel budgetModel)
	{
		budgetService.save (budgetModel);
		return requestMapper.buildGenerateResponse ("Budget saved successfully!", HttpStatus.CREATED);
	}
	@PatchMapping ("/editBudget")
	public ResponseEntity <ResponseStructure <Void>> editBudget (@RequestBody BudgetModel budgetModel)
	{
		budgetService.edit (budgetModel);
		return requestMapper.buildGenerateResponse ("Budget edited successfully!", HttpStatus.OK);
	}
	@PostMapping ("/deleteBudget/{id}")
	public ResponseEntity <ResponseStructure <Void>> deleteBudget (@PathVariable UUID id)
	{
		budgetService.delete (id);
		return requestMapper.buildGenerateResponse ("Budget deleted successfully!", HttpStatus.OK);
	}
	@PostMapping ("/deleteAllBudgets/{id}")
	public ResponseEntity <ResponseStructure <Void>> deleteAllBudgets (@PathVariable UUID userId)
	{
		budgetService.deleteAll (userId);
		return requestMapper.buildGenerateResponse ("All budgets deleted successfully!", HttpStatus.OK);
	}
	@GetMapping ("/getAllBudgets")
	public ResponseEntity <ResponseStructure <List <BudgetDTO>>> getAllBudgets ()
	{
		return requestMapper.buildGenerateResponse ("Budget :-", HttpStatus.OK, budgetService.getAllBudgets ());
	}
}