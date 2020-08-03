package com.ssafy.devit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devit.model.reply.Reply;
import com.ssafy.devit.repository.ReplyRepository;

@Service
public class ReplyServicelmpl implements ReplyService {
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Override
	public void upload(Reply reply) {
		replyRepository.upload(reply);
	}
}
