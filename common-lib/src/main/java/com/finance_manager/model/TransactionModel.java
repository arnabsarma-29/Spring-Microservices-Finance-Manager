package com.finance_manager.model;
import com.finance_manager.util.TransactionType;
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
	@NotNull
	private TransactionType type; // INCOME / EXPENSE
}