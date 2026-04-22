package com.finance_manager.transaction_service.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.transaction_service.dto.TransactionDTO;
import com.finance_manager.transaction_service.model.TransactionModel;
import com.finance_manager.transaction_service.response.ResponseStructure;
import com.finance_manager.transaction_service.service.TransactionService;
import com.finance_manager.transaction_service.util.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@AllArgsConstructor
@RequestMapping ("/transaction")
public class TransactionController
{
	private final TransactionService transactionService;
	@PostMapping ("/save")
	public ResponseEntity <ResponseStructure <Object>> saveTransaction (@RequestBody TransactionModel transactionModel)
	{
		transactionService.save (transactionModel);
		return buildGenerateResponse ("Transaction saved successfully!");
	}
	@DeleteMapping ("/delete")
	public ResponseEntity <ResponseStructure <Object>> deleteTransaction (@RequestBody UUID id)
	{
		transactionService.delete (id);
		return buildGenerateResponse ("Transaction deleted successfully!");
	}
	@GetMapping ("/getAll")
	public ResponseEntity <ResponseStructure <List <TransactionDTO>>> getTransactions ()
	{
		return buildGenerateListResponse ("Transactions fetched successfully", transactionService.getAll ());
	}
	@GetMapping ("/getMonthlyTransactions")
	public ResponseEntity <ResponseStructure <List <TransactionDTO>>> getMonthlyTransactions ()
	{
		return buildGenerateListResponse ("The Monthly Expenditure :-", transactionService.getByMonth ());
	}
	private ResponseEntity <ResponseStructure <Object>> buildGenerateResponse (String message)
	{
		return ResponseHandler.generateResponse (true, message, HttpStatus.OK, null);
	}
	private ResponseEntity <ResponseStructure <List <TransactionDTO>>> buildGenerateListResponse (String message, List <TransactionDTO> transactionDTOs)
	{
		return ResponseHandler.generateResponse (true, message, HttpStatus.OK, transactionDTOs);
	}
}