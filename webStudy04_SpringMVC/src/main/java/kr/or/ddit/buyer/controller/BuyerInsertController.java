package kr.or.ddit.buyer.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer/buyerInsert.do")
public class BuyerInsertController{

	@Inject
	private BuyerService service;
	
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList() {
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("currentAction")
	public String currentAction() {
		return "/buyer/buyerInsert.do";
	}
	
	@ModelAttribute("buyer")
	public BuyerVO buyer() {
		return new BuyerVO();
	}

	@RequestMapping
	public String form(){
		return "buyer/buyerForm";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String process(
		@Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerVO buyer
		, Errors errors
		, Model model
	){
		String viewName = null;
		if (!errors.hasErrors()) {
//		4-1. 통과
//			5. 수정(logic 사용)
			ServiceResult result = service.createBuyer(buyer);
			switch (result) {
			case FAIL:
				model.addAttribute("message", "서버 오류");
				viewName = "buyer/buyerForm";
				break;
			default:
				viewName = "redirect:/buyer/buyerView.do?what=" + buyer.getBuyerId();
				break;
			}

		} else {
//		4-2. 불통
//			buyerForm으로 (기존 데이터, 검증 데이터)
			viewName = "buyer/buyerForm";
		}
//			
//		6. viewResolve 로 이동
		return viewName;
	}
}