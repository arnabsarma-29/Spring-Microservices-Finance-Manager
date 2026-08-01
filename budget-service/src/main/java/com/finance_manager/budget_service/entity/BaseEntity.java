package com.finance_manager.budget_service.entity;
import java.util.UUID;
import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public abstract class BaseEntity
{
	@Id
	@EqualsAndHashCode.Include
	@Column (name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;
	@PrePersist
	public void generateId ()
	{
		if (id == null)
		{
			id = UuidCreator.getTimeOrderedEpoch();
		}
	}
}