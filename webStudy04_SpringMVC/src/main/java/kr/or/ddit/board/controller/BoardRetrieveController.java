package kr.or.ddit.board.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;

//RESTful URI
// /board GET 목록 조회, 페이징과 검색은 비동기로
// /board/23 GET 상세 조회
// /board/new GET/POST 새글쓰기
// /board/23/form(GET) / /board/23(PUT) 글 수정
// /board/23 DELETE 글 삭제
// /board/23/attatch/1 다운로드

@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	private final FreeBoardService service;
	public BoardRetrieveController(FreeBoardService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public String boardListView() {
		return "board/boardList";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<FreeBoardVO> boardListData(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition 
	) {
		PagingVO<FreeBoardVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(simpleCondition);
		service.retrieveBoardList(paging);
		return paging;
	}
	
	@GetMapping("{boNo}")
	public String boardView(
		@PathVariable int boNo
		, Model model
	) {
		FreeBoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardView";
	}
	
	@GetMapping("{boNo}/attatch/{attNo}")
	public String download(@PathVariable int attNo, Model model) {
		AttatchVO attatch = service.downloadAttatch(attNo);
		model.addAttribute("attatch", attatch);
		return "boardDownloadView";
	}
}















