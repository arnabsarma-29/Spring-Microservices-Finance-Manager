package com.finance_manager.frontend_service.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.dto.UserDTO;
import com.finance_manager.frontend_service.service.FrontendService;
import com.finance_manager.model.BudgetModel;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.TransactionModel;
import com.finance_manager.model.UserDeleteModel;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Controller
@AllArgsConstructor
public class FrontendController
{
	private final FrontendService frontendService;
	private String loginCheck (Authentication authentication)
	{
		if (authentication != null && authentication.isAuthenticated () && !(authentication instanceof AnonymousAuthenticationToken))
		{
			return "redirect:/dashboard";
		}
		return null;
	}
	@GetMapping ("/")
	public String welcome ()
	{
		return "welcome";
	}
	@GetMapping ("/dashboard")
	public String getDashboard (Authentication authentication)
	{
		loginCheck (authentication);
		return "dashboard";
	}
	@GetMapping ("/profile")
	public String getProfile (Authentication authentication)
	{
		loginCheck (authentication);
		return "profile"; // profile uses getUser from user client
	}
	@GetMapping ("/getUser")
	public UserDTO getUser ()
	{
		return frontendService.getUser ();
	}
	@PostMapping ("/updatePassword")
	public String updatePassword (Authentication authentication, @RequestBody PasswordUpdateModel passwordUpdateModel, HttpSession httpSession)
	{
		loginCheck (authentication);
		frontendService.updatePassword (passwordUpdateModel);
		httpSession.invalidate ();
		return "redirect:/login";
	}
	@PutMapping ("/editName")
	public String editName (Authentication authentication, @RequestBody String name)
	{
		loginCheck (authentication);
		frontendService.editName (name);
		return "redirect:/profile";
	}
	@DeleteMapping ("/deleteUser")
	public String deleteUser (Authentication authentication, @RequestBody UserDeleteModel userDeleteModel)
	{
		loginCheck (authentication);
		frontendService.deleteUser (userDeleteModel);
		return "redirect:/login";
	}
	@GetMapping ("/expenses")
	public String expenses (Authentication authentication)
	{
		loginCheck (authentication);
		return "expenses";
		// here we show both budget and transactions budget on top and transactions in the bottom
		// for now forget about paging
		// budget in thymleaf should be on the top of the page which is static even if i scroll down it should be visible and transactions are below it
		// there is an delete transaction button beside the transactions
		// there should we button or something which you can suggest where i can modify the budget
	}
	@GetMapping ("/getAllBudgets")
	public List <BudgetDTO> getAllBudgets (Authentication authentication)
	{
		loginCheck (authentication);
		return frontendService.getAllBudgets ();
	}
	@PostMapping ("/saveBudget")
	public String saveBudget (Authentication authentication, @RequestBody BudgetModel budgetModel)
	{	
		loginCheck (authentication);
		frontendService.saveBudget (budgetModel);
		return "redirect:/expenses";
	}
	@PatchMapping ("/editBudget")
	public String editBudget (Authentication authentication, @RequestBody BudgetModel budgetModel)
	{
		loginCheck (authentication);
		frontendService.editBudget (budgetModel);
		return "redirect:/expenses";
	}
	@GetMapping ("/getMonthlyTransactions")
	public List <TransactionDTO> getMonthlyTransactions (Authentication authentication)
	{
		loginCheck (authentication);
		return frontendService.getMonthlyTransactions ();
	}
	@GetMapping ("/getAllTransactions")
	public List <TransactionDTO> getAllTransactions (Authentication authentication)
	{
		loginCheck (authentication);
		return frontendService.getAllTransactions ();
	}
	@PostMapping ("/saveTransaction")
	public String saveTransaction (Authentication authentication, @RequestBody TransactionModel transactionModel)
	{
		loginCheck (authentication);
		frontendService.saveTransaction (transactionModel);
		return "redirect:/expenses";
	}
	@DeleteMapping ("/deleteTransaction/{id}")
	public String deleteTransaction (Authentication authentication, @PathVariable UUID id)
	{
		loginCheck (authentication);
		frontendService.deleteTransaction (id);
		return "redirect:/expenses";
	}
	@GetMapping ("/logout")
	public String logout (Authentication authentication, HttpSession httpSession)
	{
		loginCheck (authentication);
		httpSession.invalidate ();
		return "redirect:/login";
	}
}