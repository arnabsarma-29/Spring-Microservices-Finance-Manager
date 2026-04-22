package com.finance_manager.transaction_service.dto;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
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
public class TransactionDTO
{
	@NotBlank
	private UUID id;
	@NotBlank
	private UUID userId;
	private Double amount;
	private String category;
	private String type;
}