package com.ssafy.devit.service;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.devit.repository.MailRepository;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	MailRepository mailRepository;

	@Override
	public void updateEmailConfirm(String email) throws Exception {
		sendMail(email);
	}
	
	public void sendMail(String email) throws Exception{
		int code = getRandomCode();
		mailRepository.updateCode(email, code);
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("DevIT 이메일 인증 메일입니다.");
		message.setFrom(new InternetAddress("msno2@naver.com"));
		message.addRecipient(RecipientType.TO, new InternetAddress(email));
		String text = "아래 링크를 누르면 인증이 완료 됩니다.\n";
		text += "<a href='localhost:8080/api/v1/mail/confirm?email=" + email + "&code=" + code + "'>redirect URL</a>";
		message.setText(text, "UTF-8", "html");
		javaMailSender.send(message);
	}
	
	public int getRandomCode() {
		return new Random().nextInt(50000);
	}

	@Override
	public int confirmEmailCode(String email, int code) throws Exception {
		return mailRepository.confirmEmailCode(email, code);
	}
}
