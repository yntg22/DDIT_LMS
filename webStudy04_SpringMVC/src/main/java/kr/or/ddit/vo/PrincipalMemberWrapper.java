package kr.or.ddit.vo;

import java.security.Principal;

public class PrincipalMemberWrapper implements Principal{
	private final MemberVO realUser;

	public PrincipalMemberWrapper(MemberVO adaptee) {
		super();
		this.realUser = adaptee;
	}

	@Override
	public String getName() {
		return realUser.getMemId();
	}
	
	public MemberVO getRealUser() {
		return realUser;
	}
}
