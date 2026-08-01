package com.finance_manager.transaction_service.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.mapper.RequestMapper;
import com.finance_manager.model.TransactionModel;
import com.finance_manager.response.ResponseStructure;
import com.finance_manager.transaction_service.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@AllArgsConstructor
@RequestMapping ("/transaction")
public class TransactionController
{
	private final TransactionService transactionService;
	private final RequestMapper requestMapper;
	@PostMapping ("/saveTransaction")
	public ResponseEntity <ResponseStructure <Void>> saveTransaction (@Valid @RequestBody TransactionModel transactionModel)
	{
		transactionService.save (transactionModel);
		return requestMapper.buildGenerateResponse ("Transaction saved successfully!", HttpStatus.CREATED);
	}
	@DeleteMapping ("/deleteTransaction/{id}")
	public ResponseEntity <ResponseStructure <Void>> deleteTransaction (@PathVariable UUID id)
	{
		transactionService.delete (id);
		return requestMapper.buildGenerateResponse ("Transaction deleted successfully!", HttpStatus.OK);
	}
	@DeleteMapping ("/deleteAllTransactions/{userId}")
	public ResponseEntity <ResponseStructure <Void>> deleteAllTransactions (@PathVariable UUID userId)
	{
		transactionService.deleteAll (userId);
		return requestMapper.buildGenerateResponse ("Transaction deleted successfully!", HttpStatus.OK);
	}
	@GetMapping ("/getAllTransactions")
	public ResponseEntity <ResponseStructure <List <TransactionDTO>>> getAllTransactions ()
	{
		return requestMapper.buildGenerateResponse ("Transactions fetched successfully", HttpStatus.OK, transactionService.getAll ());
	}
	@GetMapping ("/getMonthlyTransactions")
	public ResponseEntity <ResponseStructure <List <TransactionDTO>>> getMonthlyTransactions ()
	{
		return requestMapper.buildGenerateResponse ("The Monthly Expenditure :-", HttpStatus.OK, transactionService.getByMonth ());
	}
}