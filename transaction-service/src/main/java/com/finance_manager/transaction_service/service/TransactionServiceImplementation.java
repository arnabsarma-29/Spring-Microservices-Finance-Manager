package com.finance_manager.transaction_service.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finance_manager.transaction_service.client.UserClient;
import com.finance_manager.transaction_service.dao.TransactionDAO;
import com.finance_manager.transaction_service.dto.TransactionDTO;
import com.finance_manager.transaction_service.entity.Transaction;
import com.finance_manager.transaction_service.exception.TransactionDBException;
import com.finance_manager.transaction_service.model.TransactionModel;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class TransactionServiceImplementation implements TransactionService
{
	private final TransactionDAO transactionDAO;
	private final UserClient userClient;
	@Override
	@Transactional
	public void save (TransactionModel transactionModel)
	{
		Transaction transaction = new Transaction (userClient.getId (), transactionModel.getAmount (), transactionModel.getCategory (), transactionModel.getType ());
		try
		{
			transactionDAO.save (transaction);
		}
		catch (Exception e)
		{
			throw new TransactionDBException ("Failed to save transaction at " + LocalDateTime.now (), e);
		}
	}
	@Override
	@Transactional
	public void delete (UUID id)
	{
		try
		{
			transactionDAO.delete (id);
		}
		catch (Exception e)
		{
			throw new TransactionDBException ("Failed to delete transaction at " + LocalDateTime.now (), e);
		}
	}
	@Override
	public List <TransactionDTO> getAll ()
	{
		try
		{
			return transactionDAO.getAll (userClient.getId ()).stream ().map (this::mapToDTO).toList ();
		}
		catch (Exception e)
		{
			throw new TransactionDBException ("Failed to fetch transactions at " + LocalDateTime.now (), e);
		}
	}
	@Override
	public List <TransactionDTO> getByMonth ()
	{
		LocalDateTime now = LocalDateTime.now ();
		int month = now.getMonthValue ();
		int year = now.getYear ();
		UUID userId = userClient.getId ();
		try
		{
			return transactionDAO.getByMonth (userId, month, year).stream ().map (this::mapToDTO).toList ();
		}
		catch (Exception e)
		{
			throw new TransactionDBException ("Failed to fetch transactions at " + LocalDateTime.now (), e);
		}
	}
	private TransactionDTO mapToDTO (Transaction transaction)
	{
		return TransactionDTO.builder ().id (transaction.getId ()).userId (transaction.getUserId ()).amount (transaction.getAmount ()).category (transaction.getCategory ()).type (transaction.getType ()).build ();
	}
}