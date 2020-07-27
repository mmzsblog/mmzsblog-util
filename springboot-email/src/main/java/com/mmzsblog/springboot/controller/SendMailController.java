package com.mmzsblog.springboot.controller;

import com.mmzsblog.springboot.service.SendMail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMailController {
	@Resource
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
