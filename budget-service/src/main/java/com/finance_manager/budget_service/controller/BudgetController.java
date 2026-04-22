package com.finance_manager.budget_service.controller;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.budget_service.mapper.RequestMapper;
import com.finance_manager.budget_service.model.BudgetModel;
import com.finance_manager.budget_service.response.ResponseStructure;
import com.finance_manager.budget_service.service.BudgetService;
import lombok.AllArgsConstructor;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@AllArgsConstructor
@RequestMapping ("/budget")
public class BudgetController
{
	private final BudgetService budgetService;
	private final RequestMapper requestMapper;
	@PostMapping ("/save")
	public ResponseEntity <ResponseStructure <Object>> saveBudget (@RequestBody BudgetModel budgetModel)
	{
		budgetService.save (budgetModel);
		return requestMapper.buildGenerateResponse ("Budget saved successfully!");
	}
	@PostMapping ("/delete")
	public ResponseEntity <ResponseStructure <Object>> deleteBudget (UUID id)
	{
		budgetService.delete (id);
		return requestMapper.buildGenerateResponse ("Budget deleted successfully!");
	}
	@GetMapping ("/getBudget")
	public ResponseEntity <ResponseStructure <Object>> getBudget ()
	{
		return requestMapper.buildGenerateResponse ("Budget :-", budgetService.getBudget ());
	}
}