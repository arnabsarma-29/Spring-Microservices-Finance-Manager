package com.finance_manager.user_service.client;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "budget-service", url = "http://localhost:8501/budget")
public interface BudgetClient
{
	@PostMapping ("/deleteAll/{id}")
	ResponseEntity <ResponseStructure <Void>> deleteAllBudget (@RequestParam UUID userId);
}