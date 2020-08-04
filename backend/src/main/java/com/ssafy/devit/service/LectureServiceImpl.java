package com.ssafy.devit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.devit.model.lecture.LectureOneResponse;
import com.ssafy.devit.model.lecture.LectureRoleUsersResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.LikeDTO;
import com.ssafy.devit.model.lecture.TagResponse;
import com.ssafy.devit.model.request.LectureRequest;
import com.ssafy.devit.model.user.User;
import com.ssafy.devit.repository.LectureRepository;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	LectureRepository lectureRepository;

	@Override
	@Transactional
	public LectureOneResponse createLecture() throws Exception {
		// common id 생성
		lectureRepository.insertCommonId();
		// 값 가져오기
		long commonId = lectureRepository.selectCommonId();
		// 사용자 id 가져오기
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// 더미 프로젝트 강의 생성
		lectureRepository.insertLecture(user.getUserId(), commonId);
		
		return lectureRepository.selectLectureId(commonId);
	}
	
	@Override
	public void updateFoundationLecture(LectureRequest lecture) throws Exception {
		lectureRepository.updateFoundationLecture(lecture);
		// 태그 삽입
		for (String tagName : lecture.getTags()) {
			lectureRepository.insertTags(lecture.getCommonId(), tagName);
		}
	}
	
	@Override
	public void updateContentLecture(LectureRequest lecture) throws Exception {
		lectureRepository.updateContentLecture(lecture);
	}

	@Override
	public List<LecturesResponse> getLectures(long userId, int startPage, int type) throws Exception {
		startPage = (startPage-1) * 20;
		return lectureRepository.selectLectures(userId, startPage, type);
	}

	@Override
	public int getLectureSubCount() throws Exception {
		return lectureRepository.getLectureSubCount();
	}

	@Override
	public List<TagResponse> getTags() throws Exception {
		return lectureRepository.selectTags();
	}

	@Override
	public LectureOneResponse getLectureBylectureId(long lectureId, long userId) throws Exception {
		lectureRepository.updateLectureViewCount(lectureId);
		return lectureRepository.selectLectureByLectureId(lectureId, userId);
	}

	@Override
	public void updateLikeLectureByUserId(long lectureId, int likeType) throws Exception {
		// 사용자 id 가져오기
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LikeDTO dto = lectureRepository.checkLikeLectureByUserId(user.getUserId(), lectureId, likeType);
		if(dto.getLikeCount() > 0) {
			// 좋아요를 누른 적이 있다면
			String likeFlag = dto.getLikeFlag().equals("Y") ? "N" : "Y";
			lectureRepository.updateLikeLectureByUserId(user.getUserId(), lectureId, likeType, likeFlag);
		}else {
			// 좋아요를 누른 적이 한번도 없다면
			lectureRepository.insertLikeLectureByUserId(user.getUserId(), lectureId, likeType);
		}
	}

	@Override
	public void insertAuthLecture(long lectureId, String role) throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		lectureRepository.insertAuthLecture(lectureId, user.getUserId(), role);
	}

	@Override
	public List<LectureRoleUsersResponse> selectRoleUsersByLectureId(long lectureId) throws Exception {
		return lectureRepository.selectRoleUsersByLectureId(lectureId);
	}
}
