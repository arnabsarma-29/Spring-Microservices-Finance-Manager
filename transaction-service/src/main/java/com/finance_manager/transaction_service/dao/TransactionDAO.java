package com.finance_manager.transaction_service.dao;
import java.util.List;
import java.util.UUID;
import com.finance_manager.transaction_service.entity.Transaction;
public interface TransactionDAO
{
	void save (Transaction transaction);
	void delete (UUID id);
	List <Transaction> getAll (UUID id);
	List <Transaction> getByMonth (UUID userId, int month, int year);
}