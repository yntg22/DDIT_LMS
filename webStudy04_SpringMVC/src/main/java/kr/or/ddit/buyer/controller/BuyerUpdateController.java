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
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer/buyerUpdate.do")
public class BuyerUpdateController{

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
		return "/buyer/buyerUpdate.do";
	}

	@RequestMapping
	public String form(@RequestParam("what") String buyerId, Model model){
		BuyerVO buyer = service.retrieveBuyer(buyerId);

		model.addAttribute("buyer", buyer);
		return "buyer/buyerForm";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("buyer") BuyerVO buyer
			, Errors errors
			, Model model){
//		3. 검증 : errors를 call by reference 방식으로 활용.
		String viewName = null;
		if (!errors.hasErrors()) {
//		4-1. 통과
//			5. 수정(logic 사용)
			ServiceResult result = service.modifyBuyer(buyer);
			switch (result) {
			case FAIL:
//				2) FAIL : buyerForm로 
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