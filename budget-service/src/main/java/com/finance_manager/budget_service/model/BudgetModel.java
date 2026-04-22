package com.finance_manager.budget_service.model;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetModel
{
	@NotNull
	private UUID userId;
	@NotBlank
	private String category; // FOOD, RENT, etc.
	@NotNull
	private Double limitAmount;
	@NotBlank
	private String month;
}