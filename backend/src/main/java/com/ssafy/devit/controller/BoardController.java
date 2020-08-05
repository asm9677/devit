package com.ssafy.devit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.model.lecture.BoardResponse;
import com.ssafy.devit.model.request.BoardRequest;
import com.ssafy.devit.model.request.BoardUpdateRequest;
import com.ssafy.devit.model.request.BoardUploadRequest;
import com.ssafy.devit.model.user.User;
import com.ssafy.devit.service.BoardService;
import com.ssafy.devit.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommonResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = CommonResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = CommonResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = CommonResponse.class) })

@RestController
@RequestMapping("/api/v1")
public class BoardController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;

	@Autowired
	BoardService boardService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@PostMapping("/board")
	@ApiOperation(value = "게시물 등록")
	public ResponseEntity<CommonResponse> upload(@RequestBody BoardUploadRequest request) throws Exception{
		log.info(">> boardUpLoad <<");
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final CommonResponse result = new CommonResponse();
		ResponseEntity<CommonResponse> response = null;
		BoardRequest board = null;
		board = new BoardRequest(user.getUserId(), request.getBoardTitle(), request.getBoardContent(), request.getBoardType(), request.getBoardCount());
		try {
			boardService.upload(board);
			 // bid에 해당하는 게시글을 조회한다.
			result.msg = "success";
//			result.result = board.getBoardId();
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@GetMapping("board/{bid}")
	@ApiOperation(value = "게시글 상세 조회")
	public ResponseEntity<CommonResponse> info(@PathVariable final long bid) throws Exception {
		log.info(">> boardinfo <<");
		// PathVariable로 bid를 받아서 해당 게시글을 조회한다.
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		BoardRequest board = null;
		BoardResponse boardResponse = null;
		try {
			
			User user = null;
			try {
				user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
				user = new User();
				user.setUserId(0);
			}

			//board = boardService.info(bid);
			boardResponse = boardService.info(bid, user.getUserId());
			
			if(boardResponse != null) {
				result.msg = "success";
				System.out.println(boardResponse.getBoardTitle());
				result.result = boardResponse;
				response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
			} else {
				result.msg = "not found";
				System.out.println("Not Found");
				response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // bid에 해당하는 게시글을 조회한다.
		return response;
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@DeleteMapping("board/{bid}")
	@ApiOperation(value = "게시글 삭제")
	public ResponseEntity<CommonResponse> delete(@PathVariable final long bid) throws Exception {
		log.info(">> board delete <<");
		// PathVariable로 bid를 받아서 해당 게시글을 삭제한다.
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			result.msg = "success";
			boardService.delete(bid);
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@PutMapping("/board")
	@ApiOperation(value = "게시물 수정")
	public ResponseEntity<CommonResponse> update(@RequestBody BoardUpdateRequest request) throws Exception{
		log.info(">> boardUpdate <<");
		final CommonResponse result = new CommonResponse();
		ResponseEntity<CommonResponse> response = null;
		//Board board = null;
		BoardResponse boardResponse = null;
		try {
			User user = null;
			try {
				user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
				user = new User();
				user.setUserId(0);
			}

			boardResponse = boardService.info(request.getBoardId(), user.getUserId());
			if(boardResponse != null) {
				boardResponse.setBoardTitle(request.getBoardTitle());
				boardResponse.setBoardContent(request.getBoardContent());
				boardResponse.setBoardType(request.getBoardType());
				boardResponse.setBoardCount(request.getBoardCount());
				boardService.update(boardResponse, request.getBoardId());
				result.msg = "Success";
				result.result = request.getBoardId();
				response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
			} else {
				result.msg = "not found";
				System.out.println("Not Found");
				response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
//	@GetMapping("board/list/{type}")
	@GetMapping("board/list")
	@ApiOperation(value = "게시글 목록 조회")
//	public ResponseEntity<CommonResponse> listinfo(@PathVariable final long type) throws Exception {
	public ResponseEntity<CommonResponse> listinfo(@RequestParam("page") int startPage,
			@RequestParam("type") int type, @RequestParam("itemsperpage") int itemsperpage,
			@ApiParam(value = "검색조건", required = false)@RequestParam("searchselect") String searchselect, 
			@ApiParam(value = "검색할단어", required = false)@RequestParam("searchtxt") String searchtxt) throws Exception {
		log.info(">> board list info <<");
		// PathVariable로 type를 받아서 해당 게시글 목록을 조회한다.
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			result.msg = "success";
//			result.result = boardService.listinfo(type);
			result.result = boardService.listinfo(startPage, type, itemsperpage, searchselect, searchtxt);
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
