package com.finance_manager.transaction_service.mapper;
import java.util.UUID;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.model.TransactionModel;
import com.finance_manager.transaction_service.entity.Transaction;
import com.finance_manager.util.TransactionType;
public class TransactionMapperImplementation implements TransactionMapper
{
	@Override
	public Transaction modelToEntity (TransactionModel transactionModel, UUID userId)
	{
		return new Transaction (userId, transactionModel.getAmount (), transactionModel.getCategory (), mapToEntityEnum (transactionModel.getType ()));
	}
	@Override
	public TransactionDTO entityToDTO (Transaction transaction)
	{
		return TransactionDTO.builder ().id (transaction.getId ()).userId (transaction.getUserId ()).amount (transaction.getAmount ()).category (transaction.getCategory()).type (mapTransactionType (transaction.getType ())).localDateTime (transaction.getLocalDateTime ()).build ();
	}
	private TransactionType mapTransactionType (TransactionType entityType)
	{
		if (entityType == null)
		{
			return null;
		}
		return TransactionType.valueOf (entityType.name ());
	}
	private TransactionType mapToEntityEnum (TransactionType utilType)
	{
		if (utilType == null)
		{
			return null;
		}
		return TransactionType.valueOf (utilType.name ());
	}
}