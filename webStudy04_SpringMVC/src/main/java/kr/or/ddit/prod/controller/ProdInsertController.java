package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/prod/prodInsert.do")
@Slf4j
public class ProdInsertController{
	@Inject
	private ProdService service;

	
	@ModelAttribute("command")
	public String command() {
		return "INSERT";
	}
	
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	}
	
	@GetMapping
	public String form(Model model){
		return "prod/prodForm";
	}
	
	@PostMapping
	public String process(
		@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
		, BindingResult errors
		, Model model
	) throws IOException{

		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
			}else {
				model.addAttribute("message", "서버 오류, 잠시 뒤 다시하셈.");
				viewName = "prod/prodForm";
			}
		}else {
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}
