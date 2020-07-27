package com.mmzsblog.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMail {

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    // 简单消息邮件
    public void sendSimpleMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置邮件主题
        simpleMailMessage.setSubject("账号激活");
        // 设置要发送的邮件内容
        simpleMailMessage.setText("hello!");
        // 要发送的目标邮箱
        simpleMailMessage.setTo("yyyyyyyyy@163.com");
        // 发送者邮箱和配置文件中的邮箱一致
        simpleMailMessage.setFrom("xxxxxxxxxx@qq.com");
        javaMailSenderImpl.send(simpleMailMessage);
    }

    // 复杂邮件
    public void sendMimeMail() {
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        try {
            // 开启文件上传
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 设置文件主题
            mimeMessageHelper.setSubject("账号激活");
            // 设置文件内容 第二个参数设置是否支持html
            mimeMessageHelper.setText("<b style='color:red'>账号激活，请点击我</b>", true);
            // 设置发送到的邮箱
            mimeMessageHelper.setTo("yyyyyyyyy@163.com");
            // 设置发送人和配置文件中邮箱一致
            mimeMessageHelper.setFrom("xxxxxxxxxx@qq.com");
            // 上传附件
            // mimeMessageHelper.addAttachment("", new File(""));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSenderImpl.send(mimeMessage);
    }
}
