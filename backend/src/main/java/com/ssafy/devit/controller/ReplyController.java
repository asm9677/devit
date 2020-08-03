//package com.ssafy.devit.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.devit.model.CommonResponse;
//import com.ssafy.devit.model.board.Board;
//import com.ssafy.devit.model.reply.Reply;
//import com.ssafy.devit.model.request.BoardUpdateRequest;
//import com.ssafy.devit.model.request.ReplyRequest;
//import com.ssafy.devit.model.user.User;
//import com.ssafy.devit.service.BoardService;
//import com.ssafy.devit.service.UserService;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommonResponse.class),
//		@ApiResponse(code = 403, message = "Forbidden", response = CommonResponse.class),
//		@ApiResponse(code = 404, message = "Not Found", response = CommonResponse.class),
//		@ApiResponse(code = 500, message = "Failure", response = CommonResponse.class) })
//
//@RestController
//@RequestMapping("/api/v1")
//public class ReplyController {
//	Logger log = LoggerFactory.getLogger(this.getClass());
//	
//	@Autowired
//	UserService userService;
//
//	@Autowired
//	BoardService boardService;
//	
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
//	@PostMapping("/reply/upload")
//	@ApiOperation(value = "댓글 등록")
//	public ResponseEntity<CommonResponse> upload(@RequestBody ReplyRequest request){
//		log.info(">> Reply UpLoad <<");
//		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		final CommonResponse result = new CommonResponse();
//		ResponseEntity<CommonResponse> response = null;
//		Reply reply = new Reply(request.getBoardId(), request.getUserId(), request.getReplyCreated(), request.getReplyContent(), request.getParentReplyId());
//		
//		replyService.upload(reply);
//		
//		
//		result.msg = "Success";
//		response = new ResponseEntity<CommonResponse>(result, HttpStatus.CREATED);
//		return response;
//	}
//	
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
//	@GetMapping("board/{bid}")
//	@ApiOperation(value = "게시글 조회")
//	public ResponseEntity<CommonResponse> info(@PathVariable final long bid) {
//		log.info(">> boardinfo <<");
//		// PathVariable로 bid를 받아서 해당 게시글을 조회한다.
//		ResponseEntity<CommonResponse> response = null;
//		final CommonResponse result = new CommonResponse();
//		Board board = null;
//		try {
//			board = boardService.info(bid);
//			if(board != null) {
//				result.msg = "success";
//				System.out.println(board.getBoardTitle());
//				result.result = board;
//				response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
//			} else {
//				result.msg = "not found";
//				System.out.println("Not Found");
//				response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // bid에 해당하는 게시글을 조회한다.
//		return response;
//	}
//	
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
//	@DeleteMapping("board/{bid}")
//	@ApiOperation(value = "게시글 삭제")
//	public ResponseEntity<CommonResponse> delete(@PathVariable final long bid) {
//		log.info(">> board delete <<");
//		// PathVariable로 bid를 받아서 해당 게시글을 삭제한다.
//		ResponseEntity<CommonResponse> response = null;
//		final CommonResponse result = new CommonResponse();
//		boardService.delete(bid);
//		response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
//		return response;
//	}
//	
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
//	@PutMapping("/board/update")
//	@ApiOperation(value = "게시물 수정")
//	public ResponseEntity<CommonResponse> update(@RequestBody BoardUpdateRequest request) throws Exception{
//		log.info(">> boardUpdate <<");
//		final CommonResponse result = new CommonResponse();
//		ResponseEntity<CommonResponse> response = null;
//		Board board = null;
//		board = boardService.info(request.getBoardId());
//		// DB에서 bid로 데이터 조회해서 객체로 받음
//		board.setBoardTitle(request.getBoardTitle());
//		board.setBoardContent(request.getBoardContent());
//		board.setBoardType(request.getBoardType());
//		board.setBoardCount(request.getBoardCount());
//		board.setBoardModified(request.getBoardModified());
//		
//		boardService.update(board, request.getBoardId());
//		result.msg = "Success";
//		response = new ResponseEntity<CommonResponse>(result, HttpStatus.CREATED);
//		return response;
//	}
//}
