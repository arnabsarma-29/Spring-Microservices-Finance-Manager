package com.finance_manager.frontend_service.service;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.frontend_service.client.TransactionClient;
import com.finance_manager.model.TransactionModel;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class TransactionServiceImplementation implements TransactionService
{
	private final TransactionClient transactionClient;
	@Override
	public List <TransactionDTO> getMonthlyTransactions ()
	{
		return transactionClient.getMonthlyTransactions ().getData ();
	}
	@Override
	public List <TransactionDTO> getAllTransactions ()
	{
		return transactionClient.getAllTransactions ().getData ();
	}
	@Override
	public void saveTransaction (TransactionModel transactionModel)
	{
		transactionClient.saveTransaction (transactionModel);
	}
	@Override
	public void deleteTransaction (UUID id)
	{
		transactionClient.deleteTransaction (id);
	}
}