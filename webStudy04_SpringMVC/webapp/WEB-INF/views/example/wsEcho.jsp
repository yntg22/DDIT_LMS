<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<input type="button" value="connect" id="connBtn" />
<input type="button" value="disconnect" id="disConnBtn" disabled/>
<input type="text" id="msgInput" disabled/> 
<div id="msgArea"></div>
<script type="text/javascript">
	let logHandler = function(event){
		console.log(event);
	}
	let sock = null;
	let urlInput = document.getElementById("urlInput");
	let connBtn = document.getElementById("connBtn");
	let disConnBtn = document.getElementById("disConnBtn");
	let msgInput = document.getElementById("msgInput");
	let msgArea = document.getElementById("msgArea");
	let writeMessage = function(message){
		let pTag = document.createElement("p");
		pTag.innerHTML = message;
		msgArea.appendChild(pTag);
	}
	connBtn.onclick = function (event){
		if(window.WebSocket){
			let host = location.host;
			let protocol = location.protocol == "https:" ? "wss:" : "ws:";
			let port = location.port;
			let url = protocol+"//"+host+(port?":"+port:"")+"${cPath}/spring/textEcho";
			sock = new WebSocket(url);
		}else{
			sock = new SockJS('${cPath}/spring/textEcho');
		}
		sock.onopen=function(event){
			logHandler(event);
			connBtn.disabled = true;
			msgInput.disabled = disConnBtn.disabled = false;
		};
		sock.onclose=function(event){
			logHandler(event);
			msgInput.disabled = disConnBtn.disabled = true;
			connBtn.disabled = false;
		};
		sock.onerror=logHandler;
		sock.onmessage=function(event){
			let message = event.data;
			writeMessage(message);
			logHandler(event);
		};
	}
	disConnBtn.onclick = function(event){
		sock?.close?.();
	}
	msgInput.onchange = function(event){
		let message = msgInput.value;
		if(!message) return false;
		sock?.send?.(message);
		writeMessage(message);
		msgInput.value = "";
	} 
	window.addEventListener("unload", function(){
		disConnBtn.click();	
	});
</script>