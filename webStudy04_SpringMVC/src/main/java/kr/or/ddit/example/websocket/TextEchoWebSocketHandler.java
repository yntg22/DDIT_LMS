package kr.or.ddit.example.websocket;

import java.net.InetSocketAddress;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PrincipalMemberWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextEchoWebSocketHandler extends TextWebSocketHandler {
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log.error("웹소켓 세션 {} 에서 예외 발생", session.getId(), exception);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		session.sendMessage(message);
	}

	private MemberVO getAuthenticatedUser(WebSocketSession session) {
		PrincipalMemberWrapper principal = (PrincipalMemberWrapper) session.getPrincipal();
		if(principal!=null)
			return principal.getRealUser();
		else 
			return null;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		InetSocketAddress clientAddr = session.getRemoteAddress();
		MemberVO authMember = getAuthenticatedUser(session);
		log.info("{} 과의 websocket 연결 성공, \n접속 유저 : {}", clientAddr, authMember);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		InetSocketAddress clientAddr = session.getRemoteAddress();
		int code = status.getCode();
		String reason = status.getReason();
		MemberVO authMember = getAuthenticatedUser(session);
		log.info("{} 과의 websocket 연결 종료, 종료코드 : {}, 종료사유 : {}\n, 종료 유저 : {}"
					, clientAddr, authMember, code, reason, authMember);
	}

}
