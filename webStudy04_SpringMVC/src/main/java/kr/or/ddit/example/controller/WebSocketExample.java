package kr.or.ddit.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * WebSocket 및 STOMP 예제 리뷰 전 web.xml에 등록된 필터를 확인할 것.
 *
 */
@Controller
@RequestMapping("/example")
public class WebSocketExample {
	@RequestMapping({"wsEcho", "stompEcho", "stompPush"})
	public void echoView() {}
}
