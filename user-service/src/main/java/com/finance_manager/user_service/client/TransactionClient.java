package com.finance_manager.user_service.client;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "transaction-service", url = "http://localhost:8503/transaction")
public interface TransactionClient
{
	ResponseEntity <ResponseStructure <Void>> deleteAllTransactions (@RequestParam UUID id);
}