package kr.or.ddit.address.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.address.dao.AddressDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/address", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AddressRestController{
	private final AddressDAO dao;
	public AddressRestController(AddressDAO dao) {
		super();
		this.dao = dao;
	}
//	/address/3
	@GetMapping("{addId}")
	public AddressVO detail(@PathVariable(required=true) Integer addId) {
		return dao.selectAddress(addId);
	}
	
	@GetMapping
	public List<AddressVO> list(){
		List<AddressVO> addressList = dao.selectAddressList();
		return addressList;
	}
	
//	스프링의 객체 검증 단계
//	1. hibernate-validator 의존성 추가.(pom.xml)
//	2. 검증 대상이 command object 앞에 
//		@Valid, @Validated(group hint) 사용.
//	3. command object 타입에 검증 룰을 의미하는 어노테이션 사용. ex) @NotBlank
//    4. command object 다음(****)에 검증 결과를 받기 위한,
//       BindingResult 나 Errors 객체를 선언함.
	
	@PostMapping("new")
	public Map<String, Object> insert(
		@Validated(InsertGroup.class) @ModelAttribute("address") AddressVO addVO
		, BindingResult errors
	) {
		Map<String, Object> resultMap = new HashMap<>();
		if(!errors.hasErrors()) {
			dao.insertAddress(addVO);
			resultMap.put("result", ServiceResult.OK);
			resultMap.put("target", addVO);
		}else {
			Map<String, String> errorData = new LinkedHashMap<>();
			resultMap.put("errors", errorData);
			List<ObjectError> allErrors = errors.getAllErrors();
			for(ObjectError eachErr : allErrors) {
				String errorCode = eachErr.getCode();
				String message = eachErr.getDefaultMessage();
				String objectName = eachErr.getObjectName();
				String[] codes = eachErr.getCodes();
				log.info("codes : {}, errorCode : {}, objectName : {}, message : {}"
							, codes, errorCode, objectName, message);
				errorData.put(codes[0], message);
			}
		}
		return resultMap;
	}
}




















