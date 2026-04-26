package com.finance_manager.transaction_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.model.EmailModel;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "email-service", url = "http://localhost:8081/email")
public interface EmailClient
{
	@PostMapping ("/sendMonthlySummaryEmail")
	ResponseEntity <ResponseStructure <Void>> getMonthlyTransactions (@RequestBody EmailModel emailModel);
}