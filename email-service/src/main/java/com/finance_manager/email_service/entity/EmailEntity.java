package com.finance_manager.email_service.entity;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table (name = "email_info")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmailEntity extends BaseEntity
{
	@Column (nullable = false)
	private LocalDateTime localDateTime;
	@Column (nullable = false)
	private String sender;
	@Column (nullable = false)
	private String receiver;
	@Column (nullable = false)
	@NotBlank
	private String subject;
	@Column (nullable = false)
	@NotBlank
	private String body;
	public EmailEntity (String sender, String receiver, String subject, String body)
	{
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.body = body;
	}
	@PrePersist
	protected void onCreate ()
	{
		this.localDateTime = LocalDateTime.now ();
	}
}