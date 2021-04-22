package com.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String to,String subject,String msg) {
		boolean f=false;
		String host="smtp.gmail.com";
		Properties prop=System.getProperties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.auth","true");

		try {
		Session session=Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("nealganatra@gmail.com", "menu@7777");
			}
		});
		session.setDebug(true);
		
		MimeMessage m=new MimeMessage(session);
		try {
		m.setFrom("nealganatra@gmail.com");
		m.setText(msg);
		m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		m.setSubject(subject);
		
		Transport.send(m);
		System.out.println("Email sent successfully!!");
		f=true;
		
		}catch(Exception e) {
			f=false;
			e.printStackTrace();
		}
		}catch (Exception e) {
			f=false;
			e.printStackTrace();
			
		}
		return f;
	}
}
