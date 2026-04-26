package com.finance_manager.user_service.entity;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
@Entity
@Table (name = "user_profile")
@Data
@Builder
public class User
{
	@Id // Get From Auth's /register API
	private UUID id;
	@Column (nullable = false)
	private String email;
	private String name;
}