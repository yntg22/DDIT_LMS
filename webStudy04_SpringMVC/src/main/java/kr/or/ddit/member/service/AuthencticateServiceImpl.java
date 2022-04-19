package kr.or.ddit.member.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.utils.PasswordUtils;
import kr.or.ddit.vo.MemberVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthencticateServiceImpl implements AuthenticateService {
	private final MemberDAO dao;
	
	@Override
	public Object authenticate(MemberVO input) {
		Object retValue = null;
		MemberVO saved = dao.selectMemberForAuth(input);
		if(saved!=null) {
			String inputPass = input.getMemPass();
			String savedPass = saved.getMemPass();
			if(PasswordUtils.passwordMatcher(inputPass, savedPass)) {
				retValue = saved;
			}else {
				retValue = ServiceResult.INVALIDPASSWORD;	
			}
		}else {
			retValue = ServiceResult.NOTEXIST;
		}
		return retValue;
	}

}
