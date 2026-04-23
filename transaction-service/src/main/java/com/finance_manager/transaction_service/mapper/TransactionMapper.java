package com.finance_manager.transaction_service.mapper;
import java.util.UUID;
import com.finance_manager.transaction_service.dto.TransactionDTO;
import com.finance_manager.transaction_service.entity.Transaction;
import com.finance_manager.transaction_service.model.TransactionModel;
public interface TransactionMapper
{
	Transaction modelToEntity (TransactionModel transactionModel, UUID userId);
	TransactionDTO entityToDTO (Transaction transaction);
}