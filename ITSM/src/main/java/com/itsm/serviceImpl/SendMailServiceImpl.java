package com.itsm.serviceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.itsm.model.Mail;
import com.itsm.service.SendMailService;



@Service
public class SendMailServiceImpl implements SendMailService {
	
	private final JavaMailSender javaMailSender;

	public SendMailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendMail(Mail mail) {
		SimpleMailMessage msg = new SimpleMailMessage();
		
		List<String> ls = mail.getRecipient();

		if(ls.size()==1) {
			msg.setTo(ls.get(0));
		}
		if(ls.size()==2) {
			msg.setTo(ls.get(0), ls.get(1));
		}
		
		msg.setSubject("Request Id : ##Re["+mail.getRequestId() + "]##-" + mail.getSubject());
		msg.setText(mail.getStatus());
		javaMailSender.send(msg);
	}
}
