<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<h4>PUSH message using WebSocket and STOMP</h4>
<div> LoginController 와 BoardInsertController 를 확인할 것. </div>
<div id="messageArea"></div>	
<script type="text/javascript">
	let client = null;
	let headers = {}
	let messageArea = $("#messageArea");
	function init(event) {
		var sockJS = new SockJS("${cPath}/stomp/echo");
		client = Stomp.over(sockJS);
		client.connect(headers, function(connectFrame) {
			client.subscribe("/topic/push", function(messageFrame) {
				let messageBody = JSON.parse(messageFrame.body);
				let messageType = messageBody.messageType;
				let message = messageBody.message;
				let data = messageBody.data?JSON.stringify(messageBody.data):"";
				messageArea.empty();
				messageArea.append(
					$("<p>").html("메시지 타입 : " + messageType)
					, $("<p>").html("메시지 : " + message)
					, $("<p>").html("데이터 : " + data)
				);
			});
		}, function(error) {
			console.log(error);
		});
	}
	function disconnect(event) {
		if (!client || !client.connected)
			throw "stomp 연결 수립 전";
		client.disconnect();
	}
	$(document).ready(init);
	
</script>