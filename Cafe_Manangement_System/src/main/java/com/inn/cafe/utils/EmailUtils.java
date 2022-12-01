package com.inn.cafe.utils;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

	@Autowired
	private JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to, String subject, String text, List<String> list) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("youremail");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		if(list!=null && list.size()>0) {
			message.setCc(getCcArray(list));
		}
		emailSender.send(message);
	}
	
	private String[] getCcArray(List<String> ccList) {
		String cc[] = new String[ccList.size()];
		for(int i=0;i<ccList.size();i++) {
			cc[i] = ccList.get(i);
		}
		return cc;
	}
	
	public void forgotPasswordMail(String to, String subject, String password) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("youremail");
		helper.setTo(to);
		helper.setSubject(subject);
		String htmlMsg = "<p><b>Your Login details for Cafe Management System</b><br><b>Email : </b>"+ to + "<br><b>Password : </b>"+ password +"<br><a href=\"http://localhost:4200/\">Click here to login</a></p>";
		message.setContent(htmlMsg, "text/html");
		emailSender.send(message);
	}
	
	public void sendBillEmail(String to, String name, String attachment) throws MessagingException {
		System.out.println("Inside sendBillEmail method: filename is: "+attachment);
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("youremail");
		helper.setTo(to);
		String subject = "Cafe Bill";
		helper.setSubject(subject);
		String htmlMsg = "Hi "+name+", Thanks for visiting our cafe. Below is your bill attached. Please visit again...";
		helper.setText(htmlMsg);
		FileSystemResource file = new FileSystemResource(new File(attachment));
		System.out.println("file.getFilename(): "+file.getFilename());
		helper.addAttachment(file.getFilename(), file);
		emailSender.send(message);
	}
}
