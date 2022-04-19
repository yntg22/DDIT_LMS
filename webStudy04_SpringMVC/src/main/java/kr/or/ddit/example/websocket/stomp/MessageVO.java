package kr.or.ddit.example.websocket.stomp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builderpublic class MessageVO {
	private MessageType messageType;
	private String message;
	private Object data;
	private String sender;
}
