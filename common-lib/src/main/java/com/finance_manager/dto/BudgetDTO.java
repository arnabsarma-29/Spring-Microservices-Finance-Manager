package com.finance_manager.dto;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDTO
{
	private UUID id;
	private UUID userId;
	private String category;
	private Double limitAmount;
	private Double spentAmount;
}