package com.finance_manager.transaction_service.entity;
import java.time.LocalDateTime;
import java.util.UUID;

import com.finance_manager.util.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table (name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends BaseEntity
{
	@Column (nullable = false)
	private UUID userId;
	@Column (nullable = false)
	private Double amount;
	@Column (nullable = false)
	private String category;
	@Enumerated (EnumType.STRING)
	@Column (nullable = false)
	private TransactionType type;
	@Column (nullable = false)
	private LocalDateTime localDateTime;
	public Transaction (UUID userId, Double amount, String category, TransactionType type)
	{
		this.userId = userId;
		this.amount = amount;
		this.category = category;
		this.type = type;
	}
	@PrePersist
	protected void onCreate ()
	{
		this.localDateTime = LocalDateTime.now ();
	}
}