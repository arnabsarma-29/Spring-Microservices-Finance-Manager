package com.finance_manager.transaction_service.mapper;
import java.util.UUID;
import com.finance_manager.transaction_service.dto.TransactionDTO;
import com.finance_manager.transaction_service.entity.Transaction;
import com.finance_manager.transaction_service.model.TransactionModel;
public class TransactionMapperImplementation implements TransactionMapper
{
	@Override
	public Transaction modelToEntity (TransactionModel transactionModel, UUID userId)
	{
		return new Transaction (userId, transactionModel.getAmount (), transactionModel.getCategory (), transactionModel.getType ());
	}
	@Override
	public TransactionDTO entityToDTO (Transaction transaction)
	{
		return TransactionDTO.builder ().id (transaction.getId ()).userId (transaction.getUserId ()).amount (transaction.getAmount ()).category (transaction.getCategory ()).type (transaction.getType ()).localDateTime (transaction.getLocalDateTime ()).build ();
	}
}