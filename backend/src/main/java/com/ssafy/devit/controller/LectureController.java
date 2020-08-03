package com.ssafy.devit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.model.request.LectrueRequest;
import com.ssafy.devit.model.user.User;
import com.ssafy.devit.service.LectureService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommonResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = CommonResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = CommonResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = CommonResponse.class) })
@RestController
@RequestMapping("/api/v1/lectures")
public class LectureController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LectureService lectureService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@ApiOperation(value = "대표  프로젝트 생성")
	@PostMapping
	public ResponseEntity<CommonResponse> createLecture(@RequestBody LectrueRequest request) {
		log.info(">> loadCreateLecture <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			lectureService.registLecture(request);
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			log.debug(">> Error : createLecture <<");
			log.debug(e.getMessage().toString());
		}
		return response;
	}

	@ApiOperation(value = "대표 프로젝트 목록 가져오기")
	@GetMapping
	public ResponseEntity<CommonResponse> getLectures(@RequestParam("page") int startPage,
			@RequestParam("type") int type) {
		log.info(">> loadGetLectures <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			User user = null;
			try {
				user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
				user = new User();
				user.setUserId(0);
			}
			result.result = lectureService.getLectures(user.getUserId(), startPage, type);
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : getLectures <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@ApiOperation(value = "메인페이지에 누적 강의수 가져오기")
	@GetMapping("/count")
	public ResponseEntity<CommonResponse> getLectureSubCount() {
		log.info(">> Load : getLectureSubCount <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			result.result = lectureService.getLectureSubCount();
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : getLectureSubCount <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@ApiOperation(value = "서비스에 등록된 Tag들을 상위 20개 가져오기")
	@GetMapping("/tags")
	public ResponseEntity<CommonResponse> getTags() {
		log.info(">> Load : getTags <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			result.result = lectureService.getTags();
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : getTags <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@ApiOperation(value = "대표 프로젝트 상세 정보 가져오기")
	@GetMapping("/{lectureId}")
	public ResponseEntity<CommonResponse> getLecture(@PathVariable long lectureId) {
		log.info(">> Load : getLecture <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			User user = null;
			try {
				user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
				user = new User();
				user.setUserId(0);
			}
			result.result = lectureService.getLectureBylectureId(lectureId, user.getUserId());
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : getLecture <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@ApiOperation(value = "사용자 좋아요 처리")
	@PutMapping("/like")
	public ResponseEntity<CommonResponse> updateLectureLike(@RequestParam("lectureId") long lectureId,
			@RequestParam("likeType") int likeType) {
		log.info(">> Load : updateLectureLike <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			lectureService.updateLikeLectureByUserId(lectureId, likeType);
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : updateLectureLike <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
