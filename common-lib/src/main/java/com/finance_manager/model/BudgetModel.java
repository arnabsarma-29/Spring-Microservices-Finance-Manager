package com.finance_manager.model;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BudgetModel
{
	@NotNull
	private UUID userId;
	@NotBlank
	private String category; // FOOD, RENT, etc.
	@NotNull
	private Double limitAmount;
}