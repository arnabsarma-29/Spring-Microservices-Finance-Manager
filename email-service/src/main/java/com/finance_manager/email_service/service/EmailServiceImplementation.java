package com.finance_manager.email_service.service;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.finance_manager.email_service.dao.EmailDAO;
import com.finance_manager.email_service.entity.EmailEntity;
import com.finance_manager.email_service.mapper.EmailMapper;
import com.finance_manager.exception.CustomException;
import com.finance_manager.model.EmailModel;
@Service
@Validated
public class EmailServiceImplementation implements EmailService
{
	private final JavaMailSender mailSender;
	private final EmailDAO emailDAO;
	private final String defaultSender;
	private final EmailMapper emailMapper;
	public EmailServiceImplementation (JavaMailSender mailSender, EmailDAO emailDAO, @Value ("${spring.mail.username}") String defaultSender, EmailMapper emailMapper)
	{
		this.mailSender = mailSender;
		this.emailDAO = emailDAO;
		this.defaultSender = defaultSender;
		this.emailMapper = emailMapper;
	}
	@Transactional
	private void sendSimpleEmail (EmailModel emailModel)
	{
		EmailEntity emailEntity = emailMapper.toEmailEntity (emailModel, defaultSender);
		SimpleMailMessage message = new SimpleMailMessage ();
		emailEntity.setSender (defaultSender);
		message.setFrom (emailEntity.getSender ());
		message.setTo (emailEntity.getReceiver ());
		message.setSubject (emailEntity.getSubject ());
		message.setText (emailEntity.getBody ());
		try
		{
			mailSender.send (message);
		}
		catch (Exception e)
		{
			throw new CustomException ("Email sending failed for receiver: " + emailEntity.getReceiver (), e);
		}
		try
		{
			emailDAO.saveEmailInfo (emailEntity);
		}
		catch (Exception e)
		{
			throw new CustomException ("Email save failed for receiver: " + emailEntity.getReceiver (), e);
		}
	}
	@Override
	public void registerEmail (EmailModel emailModel)
	{
		emailModel.setSubject ("Registered!");
		emailModel.setBody ("Registered Successfully Using " + emailModel.getReceiver ());
		sendSimpleEmail (emailModel);
	}
	@Override
	public void loginEmail (EmailModel emailModel)
	{
		emailModel.setSubject ("Logged In!");
		emailModel.setBody ("Logged In Using " + emailModel.getReceiver () + " at " + LocalDateTime.now ());
		sendSimpleEmail (emailModel);
	}
	@Override
	public void passwordChange (EmailModel emailModel)
	{
		emailModel.setSubject ("Password Changed!");
		emailModel.setBody ("Password updated for " + emailModel.getReceiver () + " at " + LocalDateTime.now ());
		sendSimpleEmail (emailModel);
	}
	@Override
	public void sendMonthlySummaryEmail (EmailModel emailModel)
	{
		emailModel.setSubject ("Monthly Summary :-");
		sendSimpleEmail (emailModel);
	}
}