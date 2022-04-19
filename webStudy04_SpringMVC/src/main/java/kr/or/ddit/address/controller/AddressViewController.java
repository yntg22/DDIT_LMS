package kr.or.ddit.address.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.address.dao.AddressDAO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.AddressVO;

@Controller
public class AddressViewController {
	private final AddressDAO dao;
	public AddressViewController(AddressDAO dao) {
		super();
		this.dao = dao;
	}

	// 비동기 테스트
	@RequestMapping("/address/addressMng")
	public String view() {
		return "address/view";
	}
	
	@ModelAttribute("address")
	public AddressVO address() {
		return new AddressVO();
	}
	
	@RequestMapping("/address/addressMngSync")
	public String viewSync(Model model) {
		List<AddressVO> list = dao.selectAddressList();
		model.addAttribute("addList", list);
		return "address/syncView";
	}
	
	@PostMapping("/address/addInsert.do")
	public String insertSync(
		@Validated(InsertGroup.class) @ModelAttribute("address") AddressVO addVO 
		, Errors errors
		, RedirectAttributes redirectAttributes
	) {
		if(!errors.hasErrors()) {
			dao.insertAddress(addVO);
		}else {
			redirectAttributes.addFlashAttribute("address", addVO);
			String errorName = BindingResult.MODEL_KEY_PREFIX + "address";
			redirectAttributes.addFlashAttribute(errorName, errors);
		}
		return "redirect:/address/addressMngSync";
	}
}













