package com.finance_manager.transaction_service.dao;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.finance_manager.transaction_service.entity.Transaction;
import com.finance_manager.transaction_service.repository.TransactionRepository;
import lombok.AllArgsConstructor;
@Repository
@AllArgsConstructor
public class TransactionDAOImplementation implements TransactionDAO
{
	private final TransactionRepository transactionRepository;
	@Override
	public void save (Transaction transaction)
	{
		transactionRepository.save (transaction);
	}
	@Override
	public void delete (UUID id)
	{
		transactionRepository.deleteById (id);
	}
	@Override
	public void deleteAll (UUID userId)
	{
		transactionRepository.deleteAllByUserId (userId);
	}
	@Override
	public List <Transaction> getAll (UUID id)
	{
		return transactionRepository.findByUserId (id);
	}
	@Override
	public List <Transaction> getByMonth (UUID userId, int month, int year)
	{
		return transactionRepository.getByMonth (userId, month, year);
	}
}