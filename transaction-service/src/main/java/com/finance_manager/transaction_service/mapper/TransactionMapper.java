package com.finance_manager.transaction_service.mapper;
import java.util.UUID;
import org.mapstruct.Mapper;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.model.TransactionModel;
import com.finance_manager.transaction_service.entity.Transaction;
@Mapper (componentModel = "spring")
public interface TransactionMapper
{
	Transaction modelToEntity (TransactionModel transactionModel, UUID userId);
	TransactionDTO entityToDTO (Transaction transaction);
}