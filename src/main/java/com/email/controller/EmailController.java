package com.email.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;

@RestController
public class EmailController {
Logger logger=LoggerFactory.getLogger(EmailController.class);
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/hello")
	public String hello() {
		logger.info("This is from Test Service");
		return "Hi this is from Test service";
	}

	@PostMapping("/sendEmail")
	@CrossOrigin
public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
	
		System.out.println("START senEmail Method inside Controller");
		logger.debug("START senEmail Method inside Controller");
		
		/*
		 * EmailRequest emailRequest=new EmailRequest(); emailRequest.setTo(to);
		 * emailRequest.setSubject(subject); emailRequest.setMessage(message);
		 */
		boolean f=emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
		logger.info("After SendEMail function");
		if(f==false) {
			logger.error("Email Sent Failed");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email Failed"));
		}
		else {
			logger.info("Email Sent SUccessfully");
		return ResponseEntity.ok(new EmailResponse("Email Sent successfully..."));
		}
}
}
