package kr.or.ddit.example.websocket.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PrincipalMemberWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MakePrincipalFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("인증 객체로부터 Principal 객체를 생성하기 위한 필터{} 초기화", this.getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		if(authMember!=null) {
			HttpServletRequest wrapper = new HttpServletRequestWrapper(req) {
				@Override
				public Principal getUserPrincipal() {
					return new PrincipalMemberWrapper(authMember);
				}
			};
			chain.doFilter(wrapper, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
