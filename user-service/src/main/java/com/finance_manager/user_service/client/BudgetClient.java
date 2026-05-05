package com.finance_manager.user_service.client;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.finance_manager.response.ResponseStructure;
import com.finance_manager.user_service.config.FeignConfig;
@FeignClient (name = "budget-service", url = "http://localhost:8501/budget", configuration = FeignConfig.class)
public interface BudgetClient
{
	@DeleteMapping ("/deleteAllBudgets/{userId}")
	ResponseStructure <Void> deleteAllBudgets (@PathVariable UUID userId);
}