package kr.or.ddit.listener;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Component // POJO
@Slf4j
public class CustomEventListenerInSpring {
	
	@EventListener(classes=ContextRefreshedEvent.class)
	public void refreshEventListener(ContextRefreshedEvent event) {
		WebApplicationContext container = 
				(WebApplicationContext) event.getApplicationContext();
		log.info("컨테이너 초기화되었음.{}", container);
		ServletContext application = container.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
//		===========
		application.setAttribute("usercount", 0);
		Set<MemberVO> userList = new LinkedHashSet<>();
		application.setAttribute("userList", userList);
//		===========
		
		log.info("cPath 로 {} 를 저장했음.", application.getContextPath());
	}
	
//		===========
	public void closeEventListener(ContextClosedEvent event) {
		WebApplicationContext container = 
				(WebApplicationContext) event.getApplicationContext();
		ServletContext application = container.getServletContext();
		application.removeAttribute("usercount");
		application.removeAttribute("userList");
		log.info("컨테이너 close 되었음.{}", container);
	}
//		===========
}












