package com.finance_manager.dto;
import java.time.LocalDateTime;
import java.util.UUID;
import com.finance_manager.util.TransactionType;
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
public class TransactionDTO
{
	@NotNull
	private UUID id;
	@NotNull
	private UUID userId;
	private Double amount;
	private String category;
	private TransactionType type;
	@NotNull
	private LocalDateTime localDateTime;
}