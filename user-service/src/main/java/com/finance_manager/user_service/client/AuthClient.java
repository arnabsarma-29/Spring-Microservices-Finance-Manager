package com.finance_manager.user_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "auth-service", url = "http://localhost:8500/user")
public interface AuthClient
{
	@DeleteMapping ("/delete")
	ResponseEntity <ResponseStructure <Void>> deleteUser (String password);
}