package com.finance_manager.transaction_service.service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.exception.CustomException;
import com.finance_manager.model.EmailModel;
import com.finance_manager.model.TransactionModel;
import com.finance_manager.security.CustomPrincipal;
import com.finance_manager.transaction_service.client.EmailClient;
import com.finance_manager.transaction_service.dao.TransactionDAO;
import com.finance_manager.transaction_service.mapper.TransactionMapper;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class TransactionServiceImplementation implements TransactionService
{
	private final TransactionDAO transactionDAO;
	private final TransactionMapper transactionMapper;
	private final EmailClient emailClient;
	@Override
	@Transactional
	public void save (TransactionModel transactionModel)
	{
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("Current User Not Found!"));
		try
		{
			transactionDAO.save (transactionMapper.modelToEntity (transactionModel, customPrincipal.getId ()));
		}
		catch (Exception e)
		{
			throw new CustomException ("Failed to save transaction at " + LocalDateTime.now (), e);
		}
	}
	@Override
	@Transactional
	@Modifying
	public void delete (UUID id)
	{
		try
		{
			transactionDAO.delete (id);
		}
		catch (Exception e)
		{
			throw new CustomException ("Failed to delete transaction at " + LocalDateTime.now (), e);
		}
	}
	@Override
	@Transactional
	@Modifying
	public void deleteAll (UUID userId)
	{
		transactionDAO.deleteAll (userId);
	}
	@Override
	public List <TransactionDTO> getAll ()
	{
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("Current User Not Found!"));
		try
		{
			return transactionDAO.getAll (customPrincipal.getId ()).stream ().map (transactionMapper :: entityToDTO).toList ();
		}
		catch (Exception e)
		{
			throw new CustomException ("Failed to fetch transactions at " + LocalDateTime.now (), e);
		}
	}
	@Override
	public List <TransactionDTO> getByMonth()
	{
		LocalDateTime now = LocalDateTime.now ();
		int month = now.getMonthValue ();
		int year = now.getYear ();
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("Current User Not Found!"));
		return transactionDAO.getByMonth (customPrincipal.getId (), month, year).stream ().map (transactionMapper :: entityToDTO).toList ();
	}
	@Scheduled (cron = "0 0 1 1 * ?")
	public void sendMonthlySummary ()
	{
		List <TransactionDTO> transactions = getByMonth ();
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("User context missing for schedule"));
		StringBuilder body = new StringBuilder ("Your Monthly Transactions:\n\n");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("dd MMM yyyy, hh:mm a");
		for (TransactionDTO txn : transactions)
		{
			body.append ("Date: ").append (txn.getLocalDateTime ().format (formatter)).append ("\nAmount: ₹").append (txn.getAmount ()).append ("\nCategory: ").append (txn.getCategory ()).append ("\nType: ").append (txn.getType ()).append ("\n----------------------\n");
		}
		EmailModel emailRequest = new EmailModel ();
		emailRequest.setReceiver (customPrincipal.getEmail ());
		emailRequest.setBody (body.toString ());
		emailClient.sendMonthlySummaryEmail (emailRequest);
	}
	private Optional <CustomPrincipal> getCurrentUser ()
	{
		Object principal = SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
		return (principal instanceof CustomPrincipal) ? Optional.of ((CustomPrincipal) principal) : Optional.empty ();
	}
}