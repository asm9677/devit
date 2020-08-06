package com.ssafy.devit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.model.common.Common;
import com.ssafy.devit.model.user.User;
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

	@ApiOperation(value = "검색어로 모든 정보 가져오기")
	@GetMapping("/{searchText}/{startPage}")
	public ResponseEntity<CommonResponse> searchInfoBySearchText(@PathVariable String searchText, @PathVariable int startPage) {
		log.info(">> Load : searchInfoBySearchText <<");
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
			result.result = commonService.findInfomationBySearch(user.getUserId(), searchText, startPage);
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : searchInfoBySearchText <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = e.getMessage().toString();
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping
	public Common test() {
		Common common = new Common();
		commonService.test(common);
		return common;
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
