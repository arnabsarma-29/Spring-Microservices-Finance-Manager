package com.finance_manager.frontend_service.controller;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.frontend_service.service.FrontendService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@AllArgsConstructor
public class FrontendController
{
	private final FrontendService frontendService;
	public String loginCheck (Authentication authentication)
	{
		if (authentication != null && authentication.isAuthenticated () && !(authentication instanceof AnonymousAuthenticationToken))
		{
			if (authentication.getAuthorities ().stream ().anyMatch (a -> a.getAuthority ().equals ("ROLE_ADMIN")))
			{
				return "redirect:/admin/dashboard";
			}
			else
			{
				return "redirect:/user/dashboard";
			}
		}
		return null;
	}
	@GetMapping ("/")
	public String welcome (Authentication authentication)
	{
		String redirect = loginCheck (authentication);
		return (redirect != null) ? redirect : "welcome";
	}
	@GetMapping ("/dashboard")
	public String getDashboard ()
	{
		return "dashboard";
	}
	@GetMapping ("/profile")
	public String getProfile (@RequestParam String param)
	{
		return "profile";
	}
	@GetMapping ("/transactions")
	public String getTransactions (@RequestParam String param)
	{
		// here we show both budget and transactions budget on top and transactions in the bottom
		// for now forget about paging
		// budget in tymleaf should be on the top of the page which is static even if i scroll down it shoud be visible and transactions are below it
		// there is an delete transaction button beside the transactions
		// there should we button or something which you can suggest where i can modify the budget
	}
	@PatchMapping ("/editBudget")
	public String editBudget (@RequestBody)
	{
		
	}
}
	