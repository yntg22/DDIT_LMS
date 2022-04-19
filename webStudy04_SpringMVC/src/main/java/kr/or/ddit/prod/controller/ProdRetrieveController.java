package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/prod")
@Slf4j
public class ProdRetrieveController{
	private ProdService service;
	@Inject
	public void setService(ProdService service) {
		this.service = service;
		log.info("=============================> {}", service.getClass().getName());
	}
	
	@RequestMapping("prodList.do")
	public String listUI() {
		return "prod/prodList";
	}
	
	@RequestMapping(value="prodList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ProdVO> prodList(
			@RequestParam(value="page", required=false, defaultValue="1") int[] currentPage
			, @ModelAttribute("detailCondition") ProdVO detailCondition
	){
		PagingVO<ProdVO> paging = new PagingVO<>(3, 2);
		paging.setCurrentPage(currentPage[0]);
		paging.setDetailCondition(detailCondition);
		
		service.retrieveProdList(paging);
		
		return paging;
		
	}
	
	@RequestMapping("prodView.do")
	public String view(@RequestParam("what") String prodId, Model model){
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		return "prod/prodView";
	}
	
}
















