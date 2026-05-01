package com.finance_manager.budget_service.entity;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table (name = "budgets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget extends BaseEntity
{
	@Column (nullable = false)
	private UUID userId;
	@Column (nullable = false)
	private String category;
	@Column (nullable = false)
	private Double limitAmount;
}