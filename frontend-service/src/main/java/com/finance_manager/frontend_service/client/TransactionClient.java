package com.finance_manager.frontend_service.client;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.model.TransactionModel;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "transaction-service", url = "http://localhost:8503/transaction")
public interface TransactionClient
{
	@PostMapping ("/saveTransaction")
	ResponseStructure <Void> saveTransaction (@RequestBody TransactionModel transactionModel);
	@DeleteMapping ("/deleteTransaction/{id}")
	ResponseStructure <Void> deleteTransaction (@PathVariable UUID id);
	@GetMapping ("/getAllTransactions")
	ResponseStructure <List <TransactionDTO>> getAllTransactions ();
	@GetMapping ("/getMonthlyTransactions")
	ResponseStructure <List <TransactionDTO>> getMonthlyTransactions ();
}