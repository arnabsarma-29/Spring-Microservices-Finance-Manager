package com.finance_manager.user_service.client;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.finance_manager.response.ResponseStructure;
import com.finance_manager.user_service.config.FeignConfig;
@FeignClient (name = "transaction-service", url = "http://localhost:8503/transaction", configuration = FeignConfig.class)
public interface TransactionClient
{
	@DeleteMapping ("/deleteAllTransactions/{userId}")
	ResponseStructure <Void> deleteAllTransactions (@PathVariable UUID userId);
}