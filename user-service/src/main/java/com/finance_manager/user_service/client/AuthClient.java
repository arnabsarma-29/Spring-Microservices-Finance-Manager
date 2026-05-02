package com.finance_manager.user_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "auth-service", url = "http://localhost:8500/auth")
public interface AuthClient
{
	@DeleteMapping ("/deleteAuthUser")
	ResponseStructure <Void> deleteAuthUser (@RequestBody String password);
}