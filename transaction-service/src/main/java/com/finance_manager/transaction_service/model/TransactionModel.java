package com.finance_manager.transaction_service.model;
import com.finance_manager.transaction_service.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel
{
	@NotNull
	private Double amount;
	@NotBlank
	private String category;
	@NotBlank
	private TransactionType type; // INCOME / EXPENSE
}