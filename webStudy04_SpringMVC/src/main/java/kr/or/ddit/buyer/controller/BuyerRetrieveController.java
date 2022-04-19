package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;

@Controller
public class BuyerRetrieveController{
	@Inject
    private BuyerService service;   
	
    @RequestMapping("/buyer/buyerList.do")
    public String list(
    	SimpleSearchVO simpleSearch
    	, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
    	, Model model
    ){
    	PagingVO<BuyerVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setSimpleCondition(simpleSearch);

		pagingVO.setCurrentPage(currentPage);
		List<BuyerVO> buyerList = service.retrieveBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		
		model.addAttribute("pagingVO", pagingVO);
		return "buyer/buyerList";
    }
    
    @RequestMapping("/buyer/buyerView.do")
    public String buyerView(@RequestParam("what") String buyerId, Model model){
    	
    	BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer", buyer);
		
		return "buyer/buyerView";
    }
}
