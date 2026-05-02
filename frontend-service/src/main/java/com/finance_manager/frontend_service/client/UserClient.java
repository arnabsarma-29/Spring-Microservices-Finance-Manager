package com.finance_manager.frontend_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.dto.UserDTO;
import com.finance_manager.model.UserDeleteModel;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "user-service", url = "http://localhost:8504/user")
public interface UserClient
{
	@PutMapping ("/editName")
	ResponseStructure <Void> editName (@RequestBody String name);
	@DeleteMapping ("/deleteUser")
	ResponseStructure <Void> deleteUser (@RequestBody UserDeleteModel userDeleteModel);
	@GetMapping ("/getUser")
	ResponseStructure <UserDTO> getUser ();
}