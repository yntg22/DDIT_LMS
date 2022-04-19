package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.board.service.FreeReplyService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.FreeReplyVO;
import kr.or.ddit.vo.PagingVO;

@RestController
@RequestMapping("/board/{boNo}/reply")
public class ReplyController {
	@Inject
	private FreeReplyService service;
	
	@GetMapping
	public PagingVO<FreeReplyVO> replyList(
		@PathVariable int boNo 
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
	){
		PagingVO<FreeReplyVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setCurrentPage(currentPage);
		
		FreeReplyVO detailSearch = new FreeReplyVO();
		detailSearch.setBoNo(boNo);
		pagingVO.setDetailCondition(detailSearch);
		
		service.retrieveReplyList(pagingVO);
		
		return pagingVO;
	}
	
	
	@PostMapping
	public Map<String, Object> replyInsert(
		@Validated(InsertGroup.class) FreeReplyVO reply
		, BindingResult errors
		, @RequestParam Map<String, Object> paramMap
	){
		ServiceResult result = null;
		Map<String, Object> resultMap = new HashMap<>();
		if(!errors.hasErrors()) {
			result = service.createReply(reply);
			if(ServiceResult.FAIL.equals(result)) {
				resultMap.put("message", "서버 오류");
			}
		}else {
			result = ServiceResult.INVALID;
			resultMap.put("message", "검증에 걸렸음.");
		}
		resultMap.put("result", result);
		resultMap.put("reply", reply);
		resultMap.putAll(paramMap);
		
		return resultMap;
	}
	

	@PutMapping
	public Map<String, Object> update(
		@Validated(UpdateGroup.class) FreeReplyVO reply
		, BindingResult errors
		, @RequestParam Map<String, Object> paramMap
	) {
		ServiceResult result = null;
		Map<String, Object> resultMap = new HashMap<>();
		if(!errors.hasErrors()) {
			result = service.modifyReply(reply);
			if(ServiceResult.INVALIDPASSWORD.equals(result)) {
				resultMap.put("message", "비밀번호 오류");
			}
		}else {
			result = ServiceResult.INVALID;
			resultMap.put("message", "검증에 걸렸음.");
		}
		resultMap.put("result", result);
		resultMap.putAll(paramMap);
		return resultMap;
	}
	
	@DeleteMapping
	public Map<String, Object> delete(
		@Validated(DeleteGroup.class) FreeReplyVO reply
		, BindingResult errors
		, @RequestParam Map<String, Object> paramMap

	) {
		ServiceResult result = null;
		Map<String, Object> resultMap = new HashMap<>();
		if(!errors.hasErrors()) {
			result = service.removeReply(reply);
			if(ServiceResult.INVALIDPASSWORD.equals(result)) {
				resultMap.put("message", "비밀번호 오류");
			}
		}else {
			result = ServiceResult.INVALID;
			resultMap.put("message", "검증에 걸렸음.");
		}
		resultMap.put("result", result);
		resultMap.putAll(paramMap);
		return resultMap;
	}
	
}
