package com.finance_manager.frontend_service.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import feign.FeignException;
import jakarta.servlet.http.HttpSession;
@ControllerAdvice ("frontendExceptionHandler")
public class GlobalExceptionHandler
{
	@ExceptionHandler (FeignException.Unauthorized.class)
	public String handleUnauthorized (HttpSession session, RedirectAttributes redirectAttributes)
	{
		session.invalidate ();
		redirectAttributes.addFlashAttribute ("error", "Session expired. Please login again.");
		return "redirect:/login";
	}
	@ExceptionHandler (FeignException.BadRequest.class)
	public String handleBadRequest (FeignException e, RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute ("error", "Invalid data provided.");
		return "redirect:/dashboard";
	}
	@ExceptionHandler(Exception.class)
	public String handleGeneralError (Exception e, RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute ("error", "Something went wrong: " + e.getMessage ());
		return "redirect:/dashboard";
	}
}