package com.finance_manager.budget_service.dto;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BudgetDTO
{
	private UUID id;
	private UUID userId;
	private String category;
	private Double limitAmount;
	private String month;
}