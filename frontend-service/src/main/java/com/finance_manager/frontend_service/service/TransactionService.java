package com.finance_manager.frontend_service.service;
import java.util.List;
import java.util.UUID;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.model.TransactionModel;
public interface TransactionService
{
	List <TransactionDTO> getMonthlyTransactions ();
	List <TransactionDTO> getAllTransactions ();
	void saveTransaction (TransactionModel transactionModel);
	void deleteTransaction (UUID id);
}