package com.ssafy.devit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.devit.model.common.Common;
import com.ssafy.devit.model.lecture.LectureOneResponse;
import com.ssafy.devit.model.lecture.LectureRoleUsersResponse;
import com.ssafy.devit.model.lecture.LectureSubIndexResponse;
import com.ssafy.devit.model.lecture.LectureSubOneResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.LikeDTO;
import com.ssafy.devit.model.lecture.TagResponse;
import com.ssafy.devit.model.lecture.TheOhterSubLectureResponse;
import com.ssafy.devit.model.request.LectureAuthRequest;
import com.ssafy.devit.model.request.LectureRequest;
import com.ssafy.devit.model.request.LectureSubHistoryRequest;
import com.ssafy.devit.model.request.LectureSubsRequest;
import com.ssafy.devit.model.user.User;
import com.ssafy.devit.repository.LectureRepository;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	LectureRepository lectureRepository;

	@Override
	public LectureOneResponse createDummyLecture() throws Exception { // 더미 프로젝트 생성 및 해당 프로젝트 권한 부여
		// common id 생성 및 Common id 받아오기
		Common common = new Common();
		lectureRepository.insertCommonId(common);
		
		// 사용자 id 가져오기
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// DAO에게 넘겨줄 DTO 생성
		LectureOneResponse lectureOneResponse = new LectureOneResponse();
		lectureOneResponse.setUserId(user.getUserId());
		lectureOneResponse.setCommonId(common.getCommonId());
		
		// 더미 프로젝트 강의 생성 및 권한 생성
		lectureRepository.insertLecture(lectureOneResponse);
		
		return lectureOneResponse;
	}
	
	@Override
	public void updateFoundationLecture(LectureRequest lecture) throws Exception {
		lectureRepository.updateFoundationLecture(lecture);
		lectureRepository.insertTags(lecture.getCommonId(), lecture.getTags());
	}
	
	@Override
	public void updateContentLecture(LectureRequest lecture) throws Exception {
		System.out.println(lecture.toString());
		lectureRepository.updateContentLecture(lecture);
	}

	@Override
	public List<LecturesResponse> getLectures(long userId, int startPage, int type) throws Exception {
		startPage = (startPage-1) * 20;
		return lectureRepository.selectLectures(userId, startPage, type);
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
	public void updateLikeLectureByUserId(long lectureId) throws Exception {
		// 사용자 id 가져오기
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LikeDTO dto = lectureRepository.checkLikeLectureByUserId(user.getUserId(), lectureId);
		if(dto.getLikeCount() > 0) {
			// 좋아요를 누른 적이 있다면
			String likeFlag = dto.getLikeFlag().equals("Y") ? "N" : "Y";
			lectureRepository.updateLikeLectureByUserId(user.getUserId(), lectureId, likeFlag);
		}else {
			// 좋아요를 누른 적이 한번도 없다면
			lectureRepository.insertLikeLectureByUserId(user.getUserId(), lectureId);
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

	// 소강의 생성 및 변경된 개수 반환
	@Override
	public int createSubLectures(List<LectureSubsRequest> lectures) throws Exception {
		int count = 0;
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(LectureSubsRequest lecture : lectures) {
			lecture.setUserId(user.getUserId());
			if(lecture.getSubId() == 0) {
				// 아직 db에 있지 않은 테이블이라 간주 하며 INSERT dao를 호출

				// common id 생성 및 Common id 받아오기
				Common common = new Common();
				lectureRepository.insertCommonId(common);
				
				lecture.setCommonId(common.getCommonId());
				lectureRepository.insertSubLecture(lecture);
			}else {
				// db에는 존재하지만 order를 변경 해야해 UPDATE dao를 호출
				lectureRepository.updateSubLecture(lecture);
			}
			lectureRepository.insertTags(lecture.getCommonId(), lecture.getTags());
			count++;
		}
		return count;
	}

	@Override
	public LectureSubOneResponse getOneSubLecture(long lectureId, int order) throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LectureSubsRequest request = new LectureSubsRequest();
		request.setLectureId(lectureId);
		request.setOrder(order);
		request.setUserId(user.getUserId());
		return lectureRepository.selectOneSubLecture(request);
	}

	@Override
	public List<LectureSubIndexResponse> getSubLectureIndex(long lectureId) throws Exception {
		return lectureRepository.selectSubLectureIndex(lectureId);
	}

	@Override
	public void registrySubHistory(LectureSubHistoryRequest lecture) throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		lecture.setUserId(user.getUserId());
		lectureRepository.insertSubHistory(lecture);
	}

	@Override
	public void updateLectureAuth(List<LectureAuthRequest> auths) throws Exception {
		for(LectureAuthRequest auth : auths) {
			if(auth.getAuthId() == 0) { // 서버에 등록되어있지 않은 사람
				lectureRepository.insertLectureAuth(auth);
			}else { // 서버에 등록되어있는 사람
				lectureRepository.updateLectureAuth(auth);
			}
		}
	}

	@Override
	public List<TheOhterSubLectureResponse> getTheOtherSubLectures(long subId) throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return lectureRepository.selectTheOtherSubLectures(subId, user.getUserId());
	}
}
