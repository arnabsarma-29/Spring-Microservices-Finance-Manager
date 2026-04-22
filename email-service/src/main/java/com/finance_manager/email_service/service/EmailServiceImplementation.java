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
import com.finance_manager.email_service.exception.EmailSaveException;
import com.finance_manager.email_service.exception.EmailSendingException;
import com.finance_manager.email_service.model.EmailModel;
@Service
@Validated
public class EmailServiceImplementation implements EmailService
{
	private final JavaMailSender mailSender;
	private final EmailDAO emailDAO;
	private final String defaultSender;
	public EmailServiceImplementation (JavaMailSender mailSender, EmailDAO emailDAO, @Value ("${spring.mail.username}") String defaultSender)
	{
		this.mailSender = mailSender;
		this.emailDAO = emailDAO;
		this.defaultSender = defaultSender;
	}
	@Override
	public void sendSimpleEmail (EmailModel emailModel)
	{
		SimpleMailMessage message = new SimpleMailMessage ();
		message.setFrom (defaultSender);
		message.setTo (emailModel.getReceiver ());
		message.setSubject (emailModel.getSubject ());
		message.setText (emailModel.getBody ());
		try
		{
			mailSender.send (message);
		}
		catch (Exception e)
		{
			throw new EmailSendingException ("Email sending failed for receiver: " + emailModel.getReceiver (), e);
		}
		try
		{
			saveEmailInfo (emailModel);
		}
		catch (Exception e)
		{
			throw new EmailSaveException ("Email save failed for receiver: " + emailModel.getReceiver(), e);
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
	@Transactional
	private void saveEmailInfo (EmailModel emailModel)
	{
		emailDAO.saveEmailInfo (new EmailEntity (defaultSender, emailModel.getReceiver (), emailModel.getSubject (), emailModel.getBody ()));	
	}
}