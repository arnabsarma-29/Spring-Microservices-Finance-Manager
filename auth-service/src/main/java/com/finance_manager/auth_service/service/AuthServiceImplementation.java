package com.finance_manager.auth_service.service;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.finance_manager.auth_service.client.EmailClient;
import com.finance_manager.auth_service.client.UserClient;
import com.finance_manager.auth_service.dao.UserDAO;
import com.finance_manager.auth_service.entity.User;
import com.finance_manager.auth_service.mapper.AuthMapper;
import com.finance_manager.config.JwtConfig;
import com.finance_manager.dto.AuthResponseDTO;
import com.finance_manager.dto.AuthUserDTO;
import com.finance_manager.exception.CustomException;
import com.finance_manager.model.EmailModel;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.UserLoginModel;
import com.finance_manager.model.UserModel;
import com.finance_manager.model.AdminUserModel;
import com.finance_manager.security.CustomPrincipal;
import com.finance_manager.service.JwtService;
import lombok.RequiredArgsConstructor;
@Service
@Validated
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService
{
	private final UserDAO userDAO;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final JwtConfig jwtConfig;
	private final EmailClient emailClient;
	private final AuthMapper authMapper;
	private final UserClient userClient;
	@Override
	@Transactional
	public AuthUserDTO register (AdminUserModel adminUserModel)
	{
		if (userDAO.existsByEmail (adminUserModel.getEmail ()))
		{
			throw new CustomException ("User with email " + adminUserModel.getEmail () + " already exists");
		}
		User user = authMapper.toUser (adminUserModel);
		user.setPassword (passwordEncoder.encode (user.getPassword ()));
		User savedUser = userDAO.saveUser (user);
		UserModel userModel = UserModel.builder ().name (null).email (savedUser.getEmail ()).build ();
		userClient.saveUser (userModel);
		EmailModel emailRequest = new EmailModel ();
		emailRequest.setReceiver (savedUser.getEmail ());
		emailClient.sendRegisterEmail (emailRequest);
		return authMapper.toUserDTO (savedUser);
	}
	@Override
	public AuthResponseDTO login (UserLoginModel request)
	{
		authenticationManager.authenticate (new UsernamePasswordAuthenticationToken (request.getEmail (), request.getPassword ()));
		User user = userDAO.findByEmail (request.getEmail ()).orElseThrow (() -> new CustomException ("User record not found after authentication."));
		String token = jwtService.generateToken (user.getId (), user.getEmail ());
		AuthResponseDTO authResponseDTO = new AuthResponseDTO (token, "Bearer", jwtConfig.getExpiration ());
		EmailModel emailRequest = new EmailModel ();
		emailRequest.setReceiver (request.getEmail ());
		emailClient.sendLoginEmail (emailRequest);
		return authResponseDTO;
	}
	@Override
	@Transactional
	public void updatePassword (PasswordUpdateModel passwordUpdateModel)
	{
		CustomPrincipal principal = getCurrentUser ().orElseThrow (() -> new CustomException ("User not authenticated"));
		User user = userDAO.findById (principal.getId ()).orElseThrow (() -> new CustomException ("User not found."));
		if (!passwordEncoder.matches (passwordUpdateModel.getCurrentPassword (), user.getPassword ()))
		{
			throw new CustomException ("Invalid old password");
		}
		user.setPassword (passwordEncoder.encode (passwordUpdateModel.getNewPassword ()));
		User savedUser = userDAO.saveUser (user);
		EmailModel emailRequest = new EmailModel ();
		emailRequest.setReceiver (savedUser.getEmail ());
		emailClient.sendPasswordChangeEmail (emailRequest);
	}
	@Override
	@Transactional
	public void deleteUser (String password)
	{
		CustomPrincipal principal = getCurrentUser ().orElseThrow (() -> new CustomException ("User not authenticated"));
		User user = userDAO.findById (principal.getId ()).orElseThrow (() -> new CustomException ("User not found."));
		if (!passwordEncoder.matches (password, user.getPassword ()))
		{
			throw new CustomException ("Incorrect password");
		}
		userDAO.deleteUser (user);
	}
	@Override
	public UUID getId ()
	{
		return getCurrentUser ().map (CustomPrincipal :: getId).orElseThrow (() -> new CustomException ("User not authenticated"));
	}
	@Override
	public String getEmail ()
	{
		return getCurrentUser ().map (CustomPrincipal :: getEmail).orElseThrow (() -> new CustomException ("User not authenticated"));
	}
	private Optional <CustomPrincipal> getCurrentUser ()
	{
		Object principal = SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
		return (principal instanceof CustomPrincipal) ? Optional.of ((CustomPrincipal) principal) : Optional.empty ();
	}
}