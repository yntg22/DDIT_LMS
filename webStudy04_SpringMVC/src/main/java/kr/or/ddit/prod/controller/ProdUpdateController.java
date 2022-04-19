package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {
	
	@Inject
	private ProdService service;
	
	@ModelAttribute("command")
	public String command() {
		return "UPDATE";
	}
	
	@GetMapping
	public String form(
		@RequestParam(value="what", required=true) String prodId
		, Model model
	) {
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		return "prod/prodForm";
	}
	
	@PostMapping
	public String process(
		@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod
		, Errors errors
		, Model model		
	) throws IOException {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
			}else {
				model.addAttribute("message", "서버 오류, 잠시 뒤 다시 하시오.!");
				viewName = "prod/prodForm";
			}
		}else {
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}










