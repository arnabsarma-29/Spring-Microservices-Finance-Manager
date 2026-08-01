package com.finance_manager.frontend_service.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.finance_manager.dto.AuthResponseDTO;
import com.finance_manager.frontend_service.service.UserService;
import com.finance_manager.model.AuthUserModel;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.UserDeleteModel;
import com.finance_manager.model.UserLoginModel;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
@Controller
@RequestMapping ("/profile")
@AllArgsConstructor
public class ProfileController
{
	private final UserService userService;
	@GetMapping ("/")
	public String getProfile (Model model)
	{
		model.addAttribute ("user", userService.getUser ());
		return "profile";
	}
	@PostMapping ("/register")
	public String register (@ModelAttribute AuthUserModel authUserModel)
	{
		userService.register (authUserModel);
		return "redirect:/login";
	}
	@PostMapping ("/login")
	public String login (@ModelAttribute UserLoginModel model, HttpSession session)
	{
		AuthResponseDTO response = userService.login (model);
		session.setAttribute ("token", response.getAccessToken ());
		return "redirect:/dashboard";
	}
	@PostMapping ("/updatePassword")
	public String updatePassword (@ModelAttribute PasswordUpdateModel passwordUpdateModel, HttpSession httpSession)
	{
		userService.updatePassword (passwordUpdateModel);
		httpSession.invalidate ();
		return "redirect:/login";
	}
	@PostMapping ("/editName")
	public String editName (String name)
	{
		userService.editName (name);
		return "redirect:/profile";
	}
	@PostMapping ("/deleteUser")
	public String deleteUser (@ModelAttribute UserDeleteModel userDeleteModel, HttpSession httpSession)
	{
		userService.deleteUser (userDeleteModel);
		httpSession.invalidate ();
		return "redirect:/login";
	}
}