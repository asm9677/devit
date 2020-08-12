package com.ssafy.devit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.model.common.SearchInfoIndexResponse;
import com.ssafy.devit.model.common.SearchInfoLectureResponse;
import com.ssafy.devit.model.request.SearchFormRequest;
import com.ssafy.devit.service.CommonService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommonResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = CommonResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = CommonResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = CommonResponse.class) })
@RestController
@RequestMapping("/api/v1/commons")
public class CommonController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CommonService commonService;

	@ApiOperation(value = "검색어로 프로젝트 정보들 가져오기")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SearchInfoLectureResponse.class) })
	@PostMapping("/search/lectures")
	public ResponseEntity<CommonResponse> searchInfoLectureBySearchText(@RequestBody SearchFormRequest request) {
		log.info(">> Load : searchInfoLectureBySearchText <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			result.result = commonService.findInfomationLectureBySearch(request.getSearchText(), request.getStartPage());
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : searchInfoLectureBySearchText <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = e.getMessage().toString();
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@ApiOperation(value = "검색어로 목차 정보들 가져오기")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SearchInfoIndexResponse.class) })
	@PostMapping("/search/indexs")
	public ResponseEntity<CommonResponse> searchInfoIndexBySearchText(@RequestBody SearchFormRequest request) {
		log.info(">> Load : searchInfoIndexBySearchText <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			result.result = commonService.findInfomationIndexBySearch(request.getSearchText(), request.getStartPage());
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : searchInfoIndexBySearchText <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = e.getMessage().toString();
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@ApiOperation(value = "메인화면 devit 현황 가져오기")
	@GetMapping("/main/status")
	public ResponseEntity<CommonResponse> getMainStatus(){
		
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			result.result = commonService.getMainStatus();
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : getMainInfo <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = e.getMessage().toString();
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
