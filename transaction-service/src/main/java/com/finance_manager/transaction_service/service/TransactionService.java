package com.finance_manager.transaction_service.service;
import java.util.List;
import java.util.UUID;

import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.model.TransactionModel;
public interface TransactionService
{
	void save (TransactionModel transactionModel);
	void delete (UUID id);
	List <TransactionDTO> getAll ();
	List <TransactionDTO> getByMonth ();
}