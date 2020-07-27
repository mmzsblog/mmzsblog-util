package com.mail.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mail.service.SendMail;

@RestController
public class SendMailController {
	@Autowired
	private SendMail sendMail;
	@GetMapping("/sendSimpleMail")
	public void sendSimpleMail() {
		sendMail.sendSimpleMail();
	}
	@GetMapping("/sendMimeMail")
	public void sendMimeMail() {
		sendMail.sendMimeMail();
	}
}
