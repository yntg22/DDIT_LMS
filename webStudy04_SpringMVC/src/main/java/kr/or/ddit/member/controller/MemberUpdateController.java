package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController{
	@Inject
	private MemberService service;
	
	@GetMapping
	public String form(@SessionAttribute MemberVO authMember
			, Model model){
		MemberVO member = service.retrieveMember(authMember.getMemId());
		
		model.addAttribute("member", member);
		
		return "member/memberEdit";
	}
	
	@PostMapping
	public String process(
		@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member // command object
		, BindingResult errors
		, Model model
	){
		
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				model.addAttribute("message", "비밀번호 오류");
				viewName = "member/memberEdit";
				break;
			case FAIL:
				model.addAttribute("message", "서버 오류, 잠시 뒤 다시 실행하세요.");
				viewName = "member/memberEdit";
				break;

			default:
				viewName = "redirect:/myPage.do";
				break;
			}
		}else {
			viewName = "member/memberEdit";
		}
		return viewName;
	}
}
