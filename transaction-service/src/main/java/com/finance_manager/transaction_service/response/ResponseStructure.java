package com.finance_manager.transaction_service.response;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ResponseStructure <T>
{
	private boolean success;
	private Integer statusCode;
	private String message;
	private T data;
	private LocalDateTime dateTime;
	public ResponseStructure (boolean success, Integer statusCode, String message, T data)
	{
		this.success = success;
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
		this.dateTime = LocalDateTime.now ();
	}
}