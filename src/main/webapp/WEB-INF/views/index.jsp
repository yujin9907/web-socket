<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%--	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>--%>
<%--	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>

	<link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/sockjs-client/sockjs.min.js"></script>
	<script src="/webjars/stomp-websocket/stomp.min.js"></script>

<title>Index Page</title>
</head>
<body>
<h1>소켓 테스트 페이지</h1>

<p>이 페이지 로직은, 소켓 연결을 위해 필요함</p>
<p>전역으로 사용되는 해더나, 푸터에</p>

<p>sockjs 사용, 소켓을 지원하지 않는 브라우저에서도 statepull하게 보이도록 폴링 등의 기법을 사용해서 연결되도록 하는 역할인데,
	자세히 모르겠음</p>


<input id="user" type="hidden" value="${sessionScope.user1}">
<input id="company" type="hidden" value="${sessionScope.company1}">


<button onclick="commentInsert()">
댓글작성
</button>


<div id="socketAlert">


</div>


</body>
<script>
	//댓글 등록
	function commentInsert() {

		console.debug("reply.socket", socket); // 그냥 로그 찎는 거임?

		// let data = {
		// 	writer: $("#").val(),
		// };

		let cmd = "subscribe";  // 댓글, 스크랩 등의 기능 구분
		let subscriber = $("#user").val();  // 댓글작성자
		let company = $("#company").val(); //글작성자
		// let bno = strs[3]; //게시글 번호
		// let title = strs[4]; //게시글 제목

		alert(subscriber+","+company);


		$.ajax({
			url: '/subscribe/test',
			type: 'get',
			data: insertData, // 인설트 데이터?
			processData: false, contentType: false, // 이건 또 무슨 설정인데
			enctype: 'multipart/form-data', // 왜 멀티파트인데
			success: function (data) { // 왜 서세스?
				// commentList(); //댓글 작성 후 댓글 목록 reload
				// $('[name=content]').val('');
				// $('.myEditor').summernote('reset');
				//소켓
				// if (기업이 로그인 해 있으면) {
					if (socket) {
						let socketMsg = cmd + "," + subscriber + "," + company;
						console.log(socketMsg);
						socket.send(socketMsg);
					}
				// }
			}
		});
	}
</script>


<script>
	let socket = null;
	$(document).ready(function(){
		<%--principal 인증 처리--%>
		connectWs();
	});

	//소켓
	function connectWs(){
		let ws = new SockJS("/alram");
		socket = ws;

		ws.onopen = function() {
			console.log('open');
		};

		ws.onmessage = function(event) { // 메시지를 작성하면 이렇게 보여지게 됨
			let $socketAlert = $('div#socketAlert');
			$socketAlert.html(event.data)
			$socketAlert.css('display', 'block');

		// 	setTimeout(function(){
		// 		$socketAlert.css('display','none');
		// 	}, 5000);
		};

		ws.onclose = function() {
			console.log('close');
		};


	};

</script>

</html>