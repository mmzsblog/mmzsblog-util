package com.mail.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMail {

	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	public void sendSimpleMail() {
		// 简单消息邮件
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		// 设置邮件主题
		simpleMailMessage.setSubject("账号激活");
		// 设置要发送的邮件内容
		simpleMailMessage.setText("hello!");
		// 要发送的目标邮箱
		simpleMailMessage.setTo("twoflog@163.com");
		// 发送者邮箱和配置文件中的邮箱一致
		simpleMailMessage.setFrom("529836729@qq.com");
		javaMailSenderImpl.send(simpleMailMessage);
	}

	public void sendMimeMail() {
		// 复杂邮件
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
		try {
			// 开启文件上传
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			// 设置文件主题
			mimeMessageHelper.setSubject("账号激活");
			// 设置文件内容 第二个参数设置是否支持html
			mimeMessageHelper.setText("<b style='color:red'>账号激活，请点击我</b>", true);
			// 设置发送到的邮箱
			mimeMessageHelper.setTo("twoflog@163.com");
			// 设置发送人和配置文件中邮箱一致
			mimeMessageHelper.setFrom("529836729@qq.com");
			// 上传附件
			// mimeMessageHelper.addAttachment("", new File(""));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javaMailSenderImpl.send(mimeMessage);
	}
}
