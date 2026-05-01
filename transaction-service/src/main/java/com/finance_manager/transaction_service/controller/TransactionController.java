package com.finance_manager.transaction_service.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.mapper.RequestMapper;
import com.finance_manager.model.TransactionModel;
import com.finance_manager.response.ResponseStructure;
import com.finance_manager.transaction_service.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@AllArgsConstructor
@RequestMapping ("/transaction")
public class TransactionController
{
	private final TransactionService transactionService;
	private final RequestMapper requestMapper;
	@PostMapping ("/save")
	public ResponseEntity <ResponseStructure <Void>> saveTransaction (@RequestBody TransactionModel transactionModel)
	{
		transactionService.save (transactionModel);
		return requestMapper.buildGenerateResponse ("Transaction saved successfully!", HttpStatus.CREATED);
	}
	@DeleteMapping ("/delete/{id}")
	public ResponseEntity <ResponseStructure <Void>> deleteTransaction (@RequestParam UUID id)
	{
		transactionService.delete (id);
		return requestMapper.buildGenerateResponse ("Transaction deleted successfully!", HttpStatus.OK);
	}
	@DeleteMapping ("/delete/{id}")
	public ResponseEntity <ResponseStructure <Void>> deleteAllTransactions (@RequestParam UUID id)
	{
		transactionService.deleteAll (id);
		return requestMapper.buildGenerateResponse ("Transaction deleted successfully!", HttpStatus.OK);
	}
	@GetMapping ("/getAll")
	public ResponseEntity <ResponseStructure <List <TransactionDTO>>> getTransactions ()
	{
		return requestMapper.buildGenerateResponse ("Transactions fetched successfully", HttpStatus.OK, transactionService.getAll ());
	}
	@GetMapping ("/getMonthlyTransactions")
	public ResponseEntity <ResponseStructure <List <TransactionDTO>>> getMonthlyTransactions ()
	{
		return requestMapper.buildGenerateResponse ("The Monthly Expenditure :-", HttpStatus.OK, transactionService.getByMonth ());
	}
}