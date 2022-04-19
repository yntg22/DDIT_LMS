package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
@RequestMapping("/board/{boNo}")
public class BoardUpdateController {
	@Inject
	private FreeBoardService service;
	
	@GetMapping("form")
	public String updateForm(@PathVariable int boNo, Model model) {
		if(!model.containsAttribute("board")) {
			FreeBoardVO board = service.retrieveBoard(boNo);
			model.addAttribute("board", board);
		}
		return "board/boardEdit";
	}
	
	@PutMapping
	public String updateProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("board") FreeBoardVO board
		, BindingResult errors
		, Model model		
		, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
		viewName = "redirect:/board/{boNo}/form";
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyBoard(board);
			switch (result) {
			case INVALIDPASSWORD:
				redirectAttributes.addFlashAttribute("board", board);
				redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("board", board);
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				break;
			default:
				viewName = "redirect:/board/{boNo}?tiles=true";
				break;
			}
		}else {
			redirectAttributes.addFlashAttribute("board", board);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX+"board", errors);
		}
		return viewName;
	}
	
}









