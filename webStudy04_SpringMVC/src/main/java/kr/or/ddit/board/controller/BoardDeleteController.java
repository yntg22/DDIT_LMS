package kr.or.ddit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.vo.FreeBoardVO;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BoardDeleteController {
	private final FreeBoardService service;
	
	@DeleteMapping("/board/{boNo}")
	public String deleteBoard(
		@Validated(DeleteGroup.class) @ModelAttribute FreeBoardVO board
		, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
		redirectAttributes.addFlashAttribute("board", board);
		viewName = "redirect:/board/{boNo}?tiles=true";
		if(!errors.hasErrors()) {
			ServiceResult result = service.removeBoard(board);
			switch (result) {
			case INVALIDPASSWORD:
				redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				break;
			default:
				viewName = "redirect:/board";
				break;
			}
		}else {
			redirectAttributes.addFlashAttribute("message", "글번호나 비밀번호 누락!");
		}
		return viewName;
	}
}
