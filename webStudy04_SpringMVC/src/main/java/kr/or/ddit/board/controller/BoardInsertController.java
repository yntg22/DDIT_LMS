package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.example.websocket.stomp.MessageType;
import kr.or.ddit.example.websocket.stomp.MessageVO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.FreeBoardVO;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/board/new")
@AllArgsConstructor
public class BoardInsertController {
	private final FreeBoardService service;
	
//	=================================================
	@Inject
	private SimpMessagingTemplate messagingTemplate;
//	=================================================
	
	@ModelAttribute("board")
	public FreeBoardVO board() {
		return new FreeBoardVO();
	}
	
	@GetMapping
	public String insertForm() {
		return "board/boardForm";
	}
	
	@PostMapping
	public String insertProcess(
		@Validated(InsertGroup.class) @ModelAttribute("board") FreeBoardVO board
		, BindingResult errors
		, Model model		
		, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
		viewName = "redirect:/board/new";
		if(!errors.hasErrors()) {
			ServiceResult result = service.createBoard(board);
			switch (result) {
			case FAIL:
				redirectAttributes.addFlashAttribute("board", board);
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				break;
			default:
				viewName = "redirect:/board";
				MessageVO messageBody = MessageVO.builder()
						.messageType(MessageType.BOARD)
						.message("누군가 새로 글을 썼을걸!")
						.data(board)
						.build();
				messagingTemplate.convertAndSend("/topic/push", messageBody);
				break;
			}
		}else {
			redirectAttributes.addFlashAttribute("board", board);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX+"board", errors);
		}
		return viewName;
	}
}






