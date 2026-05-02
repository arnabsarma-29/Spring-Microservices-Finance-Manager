package com.finance_manager.user_service.client;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "budget-service", url = "http://localhost:8501/budget")
public interface BudgetClient
{
	@PostMapping ("/deleteAllBudgets/{id}")
	ResponseStructure <Void> deleteAllBudgets (@PathVariable UUID userId);
}