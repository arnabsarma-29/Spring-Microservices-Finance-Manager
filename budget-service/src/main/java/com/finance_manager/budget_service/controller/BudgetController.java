package com.finance_manager.budget_service.controller;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.budget_service.service.BudgetService;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.mapper.RequestMapper;
import com.finance_manager.model.BudgetModel;
import com.finance_manager.response.ResponseStructure;
import lombok.AllArgsConstructor;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
@RestController
@AllArgsConstructor
@RequestMapping ("/budget")
public class BudgetController
{
	private final BudgetService budgetService;
	private final RequestMapper requestMapper;
	@PostMapping ("/save")
	public ResponseEntity <ResponseStructure <Void>> saveBudget (@RequestBody BudgetModel budgetModel)
	{
		budgetService.save (budgetModel);
		return requestMapper.buildGenerateResponse ("Budget saved successfully!", HttpStatus.CREATED);
	}
	@PatchMapping ("/delete")
	public ResponseEntity <ResponseStructure <Void>> editBudget (UUID id)
	{
		budgetService.edit (id);
		return requestMapper.buildGenerateResponse ("Budget edited successfully!", HttpStatus.OK);
	}
	@GetMapping ("/getBudget")
	public ResponseEntity <ResponseStructure <BudgetDTO>> getBudget ()
	{
		return requestMapper.buildGenerateResponse ("Budget :-", HttpStatus.OK, budgetService.getBudget ());
	}
}