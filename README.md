# 소켓 테스트 페이지

### 패키지 아래 있는 거 말고
### 패키지 > socket 내에서 구현
실행 결과
https://velog.io/@rainbowweb/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-STOMP%EB%A1%9C-%EC%B1%84%ED%8C%85%EB%B0%A9-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0#%EC%8B%A4%ED%96%89%EA%B2%B0%EA%B3%BC

상세보기
https://velog.io/@jkijki12/STOMP-Spring-Boot#view

69번째 줄을 보면 메시지를 보낼 때 수행되는 sendMessage 메서드가 있는데

안에 경로를 보면 "/app/chat/message"로 되어있다.

MessageController의 enter()메서드의 실질 경로가 "/app/chat/message"이기 때문에 이 쪽으로 요청이 들어감.

그러면 /topic/chat/room/{roomId}를 구독하고 있는 client에게 메시지를 전달해주는데,

81번째 줄을 보면 ws.subscribe("/topic/chat/room/roomId")로 구독 경로를 설정하고 있기 때문에 메시지를 받을 수 있게 됨.