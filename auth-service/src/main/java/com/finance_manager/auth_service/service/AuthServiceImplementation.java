package com.finance_manager.auth_service.service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.finance_manager.auth_service.client.EmailClient;
import com.finance_manager.auth_service.config.JwtConfig;
import com.finance_manager.auth_service.dao.UserDAO;
import com.finance_manager.auth_service.dto.AuthResponseDTO;
import com.finance_manager.auth_service.dto.UserDTO;
import com.finance_manager.auth_service.entity.User;
import com.finance_manager.auth_service.mapper.AuthMapper;
import com.finance_manager.auth_service.model.EmailModel;
import com.finance_manager.auth_service.model.PasswordUpdateModel;
import com.finance_manager.auth_service.model.UserLoginModel;
import com.finance_manager.auth_service.model.UserModel;
import com.finance_manager.exception.CustomException;
import jakarta.validation.Valid;
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
	private final CurrentUserProvider currentUserProvider;
	private final EmailClient emailClient;
	private final AuthMapper authMapper;
	@Override
	@Transactional
	public UserDTO register (UserModel userModel)
	{
		if (userDAO.existsByEmail (userModel.getEmail ()))
		{
			throw new CustomException ("User with email " + userModel.getEmail () + " already exists");
		}
		User user = authMapper.toUser (userModel);
		user.setPassword (passwordEncoder.encode (user.getPassword ()));
		User savedUser = userDAO.saveUser (user);
		EmailModel emailRequest = new EmailModel ();
		emailRequest.setReceiver (savedUser.getEmail ());
		emailClient.sendRegisterEmail (emailRequest);
		return authMapper.toUserDTO (savedUser);
	}
	@Override
	public AuthResponseDTO login (UserLoginModel request)
	{
		Authentication authentication = authenticationManager.authenticate (new UsernamePasswordAuthenticationToken (request.getEmail (), request.getPassword ()));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal ();
		String token = jwtService.generateToken (userDetails);
		AuthResponseDTO authResponseDTO = new AuthResponseDTO (token, "Bearer", jwtConfig.getExpiration ());
		EmailModel emailRequest = new EmailModel ();
		emailRequest.setReceiver (request.getEmail ());
		emailClient.sendLoginEmail (emailRequest);
		return authResponseDTO;
	}
	@Override
	@Transactional
	public void updatePassword (@Valid PasswordUpdateModel passwordUpdateModel)
	{
		UserDTO currentUser = currentUserProvider.getCurrentUser ();
		User user = userDAO.findByEmail (currentUser.getEmail ()).orElseThrow (() -> new CustomException ("User not found in system."));
		if (!passwordEncoder.matches (passwordUpdateModel.getCurrentPassword (), user.getPassword ()))
		{
			throw new CustomException ("Invalid old password");
		}
		user.setPassword (passwordEncoder.encode (passwordUpdateModel.getNewPassword ()));
		User savedUser = userDAO.saveUser (user);
		EmailModel emailRequest = new EmailModel ();
		emailRequest.setReceiver (savedUser.getEmail ());
		emailClient.sendPasswordChange (emailRequest);
	}
	@Override
	@Transactional
	public void deleteUser ()
	{
		UserDTO currentUser = currentUserProvider.getCurrentUser ();
		User user = userDAO.findByEmail (currentUser.getEmail ()).orElseThrow (() -> new CustomException ("User record not found."));
		userDAO.deleteUser (user);
	}
}