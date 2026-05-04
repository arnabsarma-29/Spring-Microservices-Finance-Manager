package com.finance_manager.frontend_service.controller;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.finance_manager.frontend_service.service.BudgetService;
import com.finance_manager.frontend_service.service.TransactionService;
import com.finance_manager.model.BudgetModel;
import com.finance_manager.model.TransactionModel;
import lombok.AllArgsConstructor;
@Controller
@RequestMapping ("/expenses")
@AllArgsConstructor
public class ExpensesController
{
	private final BudgetService budgetService;
	private final TransactionService transactionService;
	@GetMapping
	public String expenses (Model model)
	{
		model.addAttribute ("budgets", budgetService.getAllBudgets ());
		model.addAttribute ("transactions", transactionService.getMonthlyTransactions ());
		model.addAttribute ("budgetModel", new BudgetModel ());
		model.addAttribute ("transactionModel", new TransactionModel ());
		return "expenses";
	}
	@PostMapping ("/saveBudget")
	public String saveBudget (@ModelAttribute BudgetModel budgetModel)
	{	
		budgetService.saveBudget (budgetModel);
		return "redirect:/expenses";
	}
	@PostMapping ("/editBudget")
	public String editBudget (@ModelAttribute BudgetModel budgetModel)
	{
		budgetService.editBudget (budgetModel);
		return "redirect:/expenses";
	}
	@PostMapping ("/deleteBudget/{id}")
	public String deleteBudget (@PathVariable UUID id)
	{
		budgetService.deleteBudget (id);
		return "redirect:/expenses";
	}
	@PostMapping ("/saveTransaction")
	public String saveTransaction (@ModelAttribute TransactionModel transactionModel)
	{
		transactionService.saveTransaction (transactionModel);
		return "redirect:/expenses";
	}
	@PostMapping ("/deleteTransaction/{id}")
	public String deleteTransaction (@PathVariable UUID id)
	{
		transactionService.deleteTransaction (id);
		return "redirect:/expenses";
	}
}