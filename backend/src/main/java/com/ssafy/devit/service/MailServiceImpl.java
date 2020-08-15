package com.ssafy.devit.service;

import java.util.Base64;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.devit.model.request.SignUpRequest;
import com.ssafy.devit.repository.MailRepository;

@Service
public class MailServiceImpl implements MailService { // to MailRepository

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	MailRepository mailRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override	
//	public int confirmEmailCode(String email, int code) throws Exception {
//		return mailRepository.confirmEmailCode(email, code);
//	}
	
	public int checkEmailConfirm(String email) throws Exception{		
		return mailRepository.checkEmailConfirm(email);
	}
	
	public void sendEmailConfirmMail(String email_to) throws Exception {	
		int code = getRandomCode();
		mailRepository.updateCode(email_to, code);
		
		String email_from = "i3a101.devit.io@gmail.com";
		String subject = "[DevIT] 계정 인증 메일입니다.";		
		String text = "링크를 클릭하면  본 계정의 인증이 완료됩니다.<br /><br />";		
		text += "<a href='i3a101.p.ssafy.io:8080/api/v1/mail/passwordConfirm?email=" + email_to + "&code=" + code + "'> 계정 인증 </a>";
		text += "<br /><a href='www.naver.com'> naver </a>";
		
		sendMail(email_from, email_to, subject, text);
	}	
	
	public void updateEmailConfirm(String email) throws Exception {	
		mailRepository.updateEmailConfirm(email);
	}	
	
	public void sendPasswordFindConfirmMail(String email_to) throws Exception{
		int code = getRandomCode();
		mailRepository.updateCode(email_to, code);
		
		String email_from = "i3a101.devit.io@gmail.com";
		String subject = "[DevIT] 비밀번호 찾기 확인 메일입니다.";		
		String text = "해당 계정으로 비밀번호 찾기 요청이 발생하였습니다.<br />본인이 요청한 것이 맞다면 아래의 링크를 클릭해주세요.<br />"
				+ "링크를 클릭하면 비밀번호가 변경됩니다.<br />변경된 비밀번호는 메일로 발송됩니다.<br /><br />";
		text += "<a href='i3a101.p.ssafy.io:8080/api/v1/mail/passwordConfirm?email=" + email_to + "&code=" + code + "'> 비밀번호 변경 </a>";
		
		sendMail(email_from, email_to, subject, text);
	}
	
	
	
	
	public void updatePasswordRandom(String email_to) throws Exception{
		String password = getRandomString(12); // 12자리 랜덤 비밀번호	(70^12)
		String password_encoded = passwordEncoder.encode(password);
		
		mailRepository.updatePasswordRandom(email_to, password_encoded);
		
		String email_from = "i3a101.devit.io@gmail.com";
		String subject = "[DevIT] 비밀번호가 변경되었습니다.";		
		String text = "해당 계정의 임시 비밀번호가 발급되었습니다.<br />비밀번호 변경을 통해 개인정보를 보호하세요.<br />";
		text += "password : " + password;
				
		sendMail(email_from, email_to, subject, text);
	}
	
	public String getRandomString(int length){
		String Candidate="0123456789!@#$%^&*abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String new_password = "";
		Random ran = new Random();
		
		for(int i=0; i < length; i++)
			new_password+= Candidate.charAt(ran.nextInt(Candidate.length()));
		
		return new_password;
	}
	
	public int getRandomCode() {
		return new Random().nextInt(50000);
	}
	
	public void sendMail(String email_from, String email_to, String subject,  String text) throws Exception{		
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject(subject);
		message.setFrom(new InternetAddress(email_from));
		message.addRecipient(RecipientType.TO, new InternetAddress(email_to));
		// message.setText(text, "UTF-8", "html");
		message.setContent(text, "text/html;charset=euc-kr");
		
		javaMailSender.send(message);		
	}

	@Override
	public void sendConfirmEmail(SignUpRequest request) throws Exception{
		String email = Base64.getEncoder().encodeToString(request.getEmail().getBytes());
		String password = Base64.getEncoder().encodeToString(request.getPassword().getBytes());
		String nickname = Base64.getEncoder().encodeToString(request.getNickname().getBytes());
		
		String email_from = "i3a101.devit.io@gmail.com";
		String subject = "[DevIT] 비밀번호 찾기 확인 메일입니다.";		
		String text = "링크를 클릭하면  본 계정의 인증이 완료됩니다.<br /><br />";
		text += "<a href='i3a101.p.ssafy.io:8080/api/v1/mail/confirm/auth?e=" + email + "&p=" + password + "&n=" + nickname +"'> 계정인증 완료 </a>";
		
		sendMail(email_from, request.getEmail(), subject, text);
	}
}
