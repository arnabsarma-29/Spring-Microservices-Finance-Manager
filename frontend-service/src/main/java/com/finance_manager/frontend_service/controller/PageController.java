package com.finance_manager.frontend_service.controller;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.finance_manager.model.AuthUserModel;
import com.finance_manager.model.UserLoginModel;
import jakarta.servlet.http.HttpSession;
@Controller
public class PageController
{
	@GetMapping ("/")
	public String welcome ()
	{
		return "welcome";
	}
	@GetMapping ("/dashboard")
	public String getDashboard ()
	{
		return "dashboard";
	}
	@GetMapping ("/login")
	public String loginPage (Model model)
	{
		if (SecurityContextHolder.getContext ().getAuthentication () != null && SecurityContextHolder.getContext ().getAuthentication ().isAuthenticated () && !(SecurityContextHolder.getContext ().getAuthentication () instanceof AnonymousAuthenticationToken))
		{
			return "redirect:/dashboard";
		}
		model.addAttribute ("loginModel", new UserLoginModel ());
		return "login";
	}
	@GetMapping ("/register")
	public String registerPage (Model model)
	{
		model.addAttribute ("user", new AuthUserModel ());
		return "register";
	}
	@PostMapping ("/logout")
	public String logout (HttpSession httpSession)
	{
		httpSession.invalidate ();
		return "redirect:/login";
	}
}