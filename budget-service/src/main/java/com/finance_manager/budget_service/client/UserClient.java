package com.finance_manager.budget_service.client;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient (name = "user-service", url = "http://localhost:8083/user")
public interface UserClient
{
	@PostMapping ("/getId")
	UUID getId ();
}